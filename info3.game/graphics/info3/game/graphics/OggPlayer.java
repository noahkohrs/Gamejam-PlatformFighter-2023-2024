/*
 * Derivative work from http://www.jcraft.com/jorbis/tutorial/Tutorial.html
 * 
 * ==========================================================================
 * Original Copyright/License
 * ==========================================================================
 * 
 * Copyright &copy; Jon Kristensen, 2008.
 * All rights reserved.
 * 
 * This is version 1.0 of this source code, made to work with JOrbis 1.x. The
 * last time this file was updated was the 15th of March, 2008.
 * 
 * Version history:
 * 
 * 1.0: Initial release.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 * 
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 * 
 *   * Neither the name of jonkri.com nor the names of its contributors may be
 *     used to endorse or promote products derived from this software without
 *     specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package info3.game.graphics;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.StreamState;
import com.jcraft.jogg.SyncState;
import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.Comment;
import com.jcraft.jorbis.DspState;
import com.jcraft.jorbis.Info;

import info3.game.sound.AudioPlayer;
import info3.game.sound.AudioPlayerListener;

public class OggPlayer extends AudioPlayer implements Runnable {

  private String m_name;
  private float m_volume;
  private Thread m_worker;

  // If you wish to debug this source, please set the variable below to true.
  private final boolean m_debugMode = false;
  private final static boolean PLAY_VERBOSE = false;
  private final static boolean VOLUME_VERBOSE = false;

  /*
   * audio stream, ogg-vorbis format.
   */
  private InputStream m_inputStream;

  /*
   * We need a buffer, it's size, a count to know how many bytes we have read and
   * an index to keep track of where we are. This is standard networking stuff
   * used with read().
   */
  byte[] m_buffer;
  int m_bufferSize;
  int m_count;
  int m_length;
  int m_index;

  /*
   * JOgg and JOrbis require fields for the converted buffer. This is a buffer
   * that is modified in regards to the number of audio channels. Naturally, it
   * will also need a size.
   */
  byte[] m_convertedBuffer;
  int m_convertedBufferSize;

  // The source data line onto which data can be written.
  private SourceDataLine m_outputLine;

  // A three-dimensional an array with PCM information.
  private float[][][] m_pcmInfo;

  // The index for the PCM information.
  private int[] m_pcmIndex;

  // Here are the four required JOgg objects...
  private Packet m_joggPacket;
  private Page m_joggPage;
  private StreamState m_joggStreamState;
  private SyncState m_joggSyncState;

  // ... followed by the four required JOrbis objects.
  private DspState m_jorbisDspState;
  private Block jorbisBlock;
  private Comment m_jorbisComment;
  private Info m_jorbisInfo;

  private volatile boolean m_stop;
  private long m_duration;
  private long m_startOfPlay = -1L;
  private long m_endOfPlay = -1L;
  private boolean m_loop;

  OggPlayer(GameCanvas canvas) {
    super(canvas);
  }

  @Override
  public String getName() {
    return m_name;
  }

  @Override
  public void stop() {
    m_stop = true;
    m_worker.interrupt();
  }

  @Override
  public void playMusic(String name, InputStream is, long duration, float volume) {
    m_name = name;
    m_volume = volume;
    m_inputStream = is;
    m_duration = duration;
    m_loop = true;
    is.mark(Integer.MAX_VALUE);
    if (m_worker != null) {
      m_stop = true;
      m_worker.interrupt();
    } else {
      m_worker = new Thread(this, "AudioPlayer:" + name);
      m_worker.start();
    }
    // _play(name, is, duration, volume);
  }

  @Override
  public void playSound(String name, InputStream is, long duration, float volume, AudioPlayerListener l) {
    m_name = name;
    m_volume = volume;
    m_inputStream = is;
    m_duration = duration;
    m_listener = l;
    if (m_worker != null) {
      m_stop = true;
      m_worker.interrupt();
    } else {
      m_worker = new Thread(this, "AudioPlayer:" + name);
      m_worker.start();
    }
    // _play(name, is, duration, volume);
  }

//  private void _play(String name, InputStream is, long duration, float volume) {
//    m_name = name;
//    m_volume = volume;
//    m_inputStream = is;
//    m_duration = duration;
//    m_worker = new Thread(this, "AudioPlayer:" + name);
//    try {
//      // m_worker.setDaemon(true);
//      // m_worker.setPriority(Thread.MAX_PRIORITY);
//    } catch (Exception e) {
//    }
//    m_worker.start();
//  }

  @Override
  public void run() {
    try {
      while (true) {
        if (PLAY_VERBOSE)
          System.out.println("\nPlaying: " + m_name);
        m_inputStream.reset();
        m_startOfPlay = System.currentTimeMillis();
        if (m_duration > 0)
          m_endOfPlay = m_startOfPlay + m_duration;
        initializeJOrbis();
        if (readHeader()) {
          if (initializeSound()) {
            debugOutput("Reading the body. length=" + m_length);
            readBody();
            debugOutput("Done reading the body. length=" + m_length);
          } else
            System.out.println("Failed initializing sound");
        } else
          System.out.println("Failed reading header");
        if (!m_loop || m_stop)
          break;
        cleanUp();
        m_inputStream.reset();
      }
    } catch (IOException ex) {
      debugOutput("Got exception=" + ex);
    } finally {
      cleanUp();
    }
    try {
      if (PLAY_VERBOSE) {
        long end = m_endOfPlay;
        if (end < 0)
          end = System.currentTimeMillis();
        System.out.println("Done[" + m_name + "]: elapsed=" + (end - m_startOfPlay) + " length=" + m_length);
      }
      if (m_listener != null)
        m_listener.endOfPlay(this, m_name);
    } catch (Throwable th) {
    }
    // Closes the stream.
    try {
      if (m_inputStream != null)
        m_inputStream.close();
    } catch (Exception e) {
    }
    m_canvas.stopped(this);
  }

  /**
   * Initializes JOrbis. First, we initialize the <code>SyncState</code> object.
   * After that, we prepare the <code>SyncState</code> buffer. Then we
   * "initialize" our buffer, taking the data in <code>SyncState</code>.
   */
  static Object lock = new Object();

  private void initializeJOrbis() {
    debugOutput("Initializing JOrbis.");
    synchronized (lock) {
      m_joggPacket = new Packet();
      m_joggPage = new Page();
      m_joggStreamState = new StreamState();
      m_joggSyncState = new SyncState();

      // ... followed by the four required JOrbis objects.
      m_jorbisDspState = new DspState();
      jorbisBlock = new Block(m_jorbisDspState);
      m_jorbisComment = new Comment();
      m_jorbisInfo = new Info();

      // Initialize SyncState
      m_joggSyncState.init();

      // Prepare the to SyncState internal buffer
      m_index = 0;
      m_bufferSize = 2048;
      m_joggSyncState.buffer(m_bufferSize);

      /*
       * Fill the buffer with the data from SyncState's internal buffer. Note how the
       * size of this new buffer is different from bufferSize.
       */
      m_buffer = m_joggSyncState.data;
    }
    debugOutput("Done initializing JOrbis.");
  }

  /**
   * This method reads the header of the stream, which consists of three packets.
   * 
   * @return true if the header was successfully read, false otherwise
   */
  private boolean readHeader() throws IOException {
    debugOutput("Starting to read the header.");

    /*
     * Variable used in loops below. While we need more data, we will continue to
     * read from the InputStream.
     */
    boolean needMoreData = true;

    /*
     * We will read the first three packets of the header. We start off by defining
     * packet = 1 and increment that value whenever we have successfully read
     * another packet.
     */
    int packet = 1;

    /*
     * While we need more data (which we do until we have read the three header
     * packets), this loop reads from the stream and has a big <code>switch</code>
     * statement which does what it's supposed to do in regards to the current
     * packet.
     */
    m_index = 0;
    m_bufferSize = 2048;

    while (needMoreData) {
      // Read from the InputStream.
      m_count = m_inputStream.read(m_buffer, m_index, m_bufferSize);
      if (m_count == -1) {
        debugOutput(" end of stream");
        return false;
      }
      m_length += m_count;
      debugOutput(" read " + m_count + " bytes, index=" + m_index);
      // We let SyncState know how many bytes we read.
      if (m_joggSyncState.wrote(m_count) < 0) {
        debugError("SyncState wrote error", null);
        return false;
      }
      /*
       * We want to read the first three packets. For the first packet, we need to
       * initialize the StreamState object and a couple of other things. For packet
       * two and three, the procedure is the same: we take out a page, and then we
       * take out the packet.
       */
      switch (packet) {
      // The first packet.
      case 1: {
        // We take out a page.
        switch (m_joggSyncState.pageout(m_joggPage)) {
        // If there is a hole in the data, we must exit.
        case -1: {
          debugOutput("There is a hole in the first " + "packet data.");
          return false;
        }

        // If we need more data, we break to get it.
        case 0: {
          break;
        }

        /*
         * We got where we wanted. We have successfully read the first packet, and we
         * will now initialize and reset StreamState, and initialize the Info and
         * Comment objects. Afterwards we will check that the page doesn't contain any
         * errors, that the packet doesn't contain any errors and that it's Vorbis data.
         */
        case 1: {
          // Initializes and resets StreamState.
          m_joggStreamState.init(m_joggPage.serialno());
          m_joggStreamState.reset();

          // Initializes the Info and Comment objects.
          m_jorbisInfo.init();
          m_jorbisComment.init();

          // Check the page (serial number and stuff).
          if (m_joggStreamState.pagein(m_joggPage) == -1) {
            debugError("We got an error while " + "reading the first header page.", null);
            return false;
          }

          /*
           * Try to extract a packet. All other return values than "1" indicates there's
           * something wrong.
           */
          if (m_joggStreamState.packetout(m_joggPacket) != 1) {
            debugError("We got an error while " + "reading the first header packet.", null);
            return false;
          }

          /*
           * We give the packet to the Info object, so that it can extract the
           * Comment-related information, among other things. If this fails, it's not
           * Vorbis data.
           */
          if (m_jorbisInfo.synthesis_headerin(m_jorbisComment, m_joggPacket) < 0) {
            debugError(
                "We got an error while " + "interpreting the first packet. " + "Apparantly, it's not Vorbis data.",
                null);
            return false;
          }

          // We're done here, let's increment "packet".
          packet++;
          break;
        }
        }

        /*
         * Note how we are NOT breaking here if we have proceeded to the second packet.
         * We don't want to read from the input stream again if it's not necessary.
         */
        if (packet == 1)
          break;
      }

      // The code for the second and third packets follow.
      case 2:
      case 3: {
        // Try to get a new page again.
        switch (m_joggSyncState.pageout(m_joggPage)) {
        // If there is a hole in the data, we must exit.
        case -1: {
          debugError("There is a hole in the second " + "or third packet data.", null);
          return false;
        }

        // If we need more data, we break to get it.
        case 0: {
          break;
        }

        /*
         * Here is where we take the page, extract a packet and and (if everything goes
         * well) give the information to the Info and Comment objects like we did above.
         */
        case 1: {
          // Share the page with the StreamState object.
          m_joggStreamState.pagein(m_joggPage);

          /*
           * Just like the switch(...packetout...) lines above.
           */
          switch (m_joggStreamState.packetout(m_joggPacket)) {
          // If there is a hole in the data, we must exit.
          case -1: {
            debugError("There is a hole in the first" + "packet data.", null);
            return false;
          }

          // If we need more data, we break to get it.
          case 0: {
            break;
          }

          // We got a packet, let's process it.
          case 1: {
            /*
             * Like above, we give the packet to the Info and Comment objects.
             */
            m_jorbisInfo.synthesis_headerin(m_jorbisComment, m_joggPacket);

            // Increment packet.
            packet++;

            if (packet == 4) {
              /*
               * There is no fourth packet, so we will just end the loop here.
               */
              needMoreData = false;
            }

            break;
          }
          }

          break;
        }
        }

        break;
      }
      }

      // We get the new index and an updated buffer.
      m_index = m_joggSyncState.buffer(m_bufferSize);
      m_buffer = m_joggSyncState.data;

      /*
       * If we need more data but can't get it, the stream doesn't contain enough
       * information.
       */
      if (m_count == 0 && needMoreData) {
        debugOutput("Not enough header data was supplied.");
        return false;
      }
    }

    debugOutput("Finished reading the header. index=" + m_index);

    return true;
  }

  /**
   * This method starts the sound system. It starts with initializing the
   * <code>DspState</code> object, after which it sets up the <code>Block</code>
   * object. Last but not least, it opens a line to the source data line.
   * 
   * @return true if the sound system was successfully started, false otherwise
   */
  private boolean initializeSound() {
    debugOutput("Initializing the sound system.");
    synchronized (lock) {

      // This buffer is used by the decoding method.
      m_convertedBufferSize = m_bufferSize * 2;
      m_convertedBuffer = new byte[m_convertedBufferSize];

      // Initializes the DSP synthesis.
      m_jorbisDspState.synthesis_init(m_jorbisInfo);

      // Make the Block object aware of the DSP.
      jorbisBlock.init(m_jorbisDspState);

      // Wee need to know the channels and rate.
      int channels = m_jorbisInfo.channels;
      int rate = m_jorbisInfo.rate;

      // Creates an AudioFormat object and a DataLine.Info object.
      AudioFormat audioFormat = new AudioFormat((float) rate, 16, channels, true, false);
      DataLine.Info datalineInfo = new DataLine.Info(SourceDataLine.class, audioFormat, AudioSystem.NOT_SPECIFIED);

      // Check if the line is supported.
      if (!AudioSystem.isLineSupported(datalineInfo)) {
        System.err.println("Audio output line is not supported.");
        return false;
      }

      /*
       * Everything seems to be alright. Let's try to open a line with the specified
       * format and start the source data line.
       */
      try {
        m_outputLine = (SourceDataLine) AudioSystem.getLine(datalineInfo);
        // System.out.println(" line[" + m_name + "]=" + m_outputLine);
        m_outputLine.open(audioFormat);

        FloatControl volumeControl = (FloatControl) m_outputLine.getControl(FloatControl.Type.MASTER_GAIN);
        float vol = volumeControl.getValue();
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        vol = min + m_volume * (max - min);
        if (VOLUME_VERBOSE) {
          System.out.println("  volume min=" + min + " max=" + max);
          System.out.println("  setting volume=" + vol);
        }
        volumeControl.setValue(vol);

      } catch (LineUnavailableException exception) {
        System.err.println("The audio output line could not be opened due " + "to resource restrictions.");
        System.err.println(exception);
        return false;
      } catch (IllegalStateException exception) {
        System.err.println("The audio output line is already open.");
        System.err.println(exception);
        return false;
      } catch (SecurityException exception) {
        System.err.println("The audio output line could not be opened due " + "to security restrictions.");
        System.err.println(exception);
        return false;
      } catch (Throwable th) {
        System.err.println("The audio initialization failed.");
        System.err.println(th);
        return false;
      }

      // Start it.
      m_outputLine.start();

      /*
       * We create the PCM variables. The index is an array with the same length as
       * the number of audio channels.
       */
      m_pcmInfo = new float[1][][];
      m_pcmIndex = new int[m_jorbisInfo.channels];
    }
    debugOutput("Done initializing the sound system.");

    return true;
  }

  /**
   * This method reads the entire stream body. Whenever it extracts a packet, it
   * will decode it by calling <code>decodeCurrentPacket()</code>.
   */
  private void readBody() throws IOException {

    /*
     * Variable used in loops below, like in readHeader(). While we need more data,
     * we will continue to read from the InputStream.
     */
    boolean needMoreData = true;

    while (needMoreData) {
      if (m_stop)
        return; // we are asked to stop
      if (m_endOfPlay != -1) {
        long now = System.currentTimeMillis();
        if (m_endOfPlay <= now) {
          System.out.println("Reached duration=" + m_duration);
          return;
        }
      }

      switch (m_joggSyncState.pageout(m_joggPage)) {
      // If there is a hole in the data, we just proceed.
      case -1: {
        debugOutput("There is a hole in the data. We proceed.");
      }

      // If we need more data, we break to get it.
      case 0: {
        break;
      }

      // If we have successfully checked out a page, we continue.
      case 1: {
        // Give the page to the StreamState object.
        m_joggStreamState.pagein(m_joggPage);

        // If granulepos() returns "0", we don't need more data.
        if (m_joggPage.granulepos() == 0) {
          needMoreData = false;
          break;
        }

        // Here is where we process the packets.
        processPackets: while (true) {
          if (m_stop)
            return; // we are asked to stop
          if (m_endOfPlay != -1) {
            long now = System.currentTimeMillis();
            if (m_endOfPlay <= now)
              return;
          }
          switch (m_joggStreamState.packetout(m_joggPacket)) {
          // Is it a hole in the data?
          case -1: {
            debugOutput("There is a hole in the data, we " + "continue though.");
          }

          // If we need more data, we break to get it.
          case 0: {
            break processPackets;
          }

          /*
           * If we have the data we need, we decode the packet.
           */
          case 1: {
            decodeCurrentPacket();
          }
          }
        }

        /*
         * If the page is the end-of-stream, we don't need more data.
         */
        if (m_joggPage.eos() != 0)
          needMoreData = false;
      }
      }

      // If we need more data...
      if (needMoreData) {
        // We get the new index and an updated buffer.
        m_index = m_joggSyncState.buffer(m_bufferSize);
        if (m_index < 0) {
          // end of stream
          debugOutput(" end of stream index=" + m_index);
          return;
        }
        m_buffer = m_joggSyncState.data;
        // Read from the InputStream.
        m_count = m_inputStream.read(m_buffer, m_index, m_bufferSize);
        if (m_count == -1) {
          debugOutput(" end of stream");
          return;
        }
        debugOutput(" read " + m_count + " bytes, index=" + m_index);

        // We let SyncState know how many bytes we read.
        m_length += m_count;
        if (m_joggSyncState.wrote(m_count) < 0) {
          debugError("SyncState wrote error", null);
          return;
        }
        // There's no more data in the stream.
        if (m_count == 0)
          needMoreData = false;
      }
    }
  }

  /**
   * A clean-up method, called when everything is finished. Clears the JOgg/JOrbis
   * objects and closes the <code>InputStream</code>.
   */
  private void cleanUp() {
    debugOutput("Cleaning up.");
    synchronized (lock) {

      if (m_outputLine != null) {
        m_outputLine.drain();
        m_outputLine.stop();
        m_outputLine.flush();
        m_outputLine.close();
        m_outputLine = null;
      }

      // Clear the necessary JOgg/JOrbis objects.
      m_joggStreamState.clear();
      jorbisBlock.clear();
      m_jorbisDspState.clear();
      m_jorbisInfo.clear();
      m_joggSyncState.clear();

//    m_buffer=null;
//    m_bufferSize=2048;
//    m_joggPacket=null;
//    m_joggPage=null;
//    m_joggStreamState=null;
//    m_joggSyncState=null;
//    m_jorbisDspState=null;
//    jorbisBlock=null;
//    m_jorbisComment=null;
//    m_jorbisInfo=null;
//    m_pcmInfo=null;
//    m_pcmIndex=null;

      m_stop = false;
      m_startOfPlay = -1L;
      m_endOfPlay = -1L;

      m_index = 0;

//    m_count=0;
//    m_length=0;
//    m_convertedBuffer=null;
//    m_convertedBufferSize =0;

      debugOutput("Done cleaning up.");
    }
  }

  /**
   * Decodes the current packet and sends it to the audio output line.
   */
  private void decodeCurrentPacket() {
    int samples;

    // Check that the packet is a audio data packet etc.
    if (jorbisBlock.synthesis(m_joggPacket) == 0) {
      // Give the block to the DspState object.
      m_jorbisDspState.synthesis_blockin(jorbisBlock);
    }

    // We need to know how many samples to process.
    int range;

    /*
     * Get the PCM information and count the samples. And while these samples are
     * more than zero...
     */
    while ((samples = m_jorbisDspState.synthesis_pcmout(m_pcmInfo, m_pcmIndex)) > 0) {
      // We need to know for how many samples we are going to process.
      if (samples < m_convertedBufferSize) {
        range = samples;
      } else {
        range = m_convertedBufferSize;
      }

      // For each channel...
      for (int i = 0; i < m_jorbisInfo.channels; i++) {
        int sampleIndex = i * 2;

        // For every sample in our range...
        for (int j = 0; j < range; j++) {
          /*
           * Get the PCM value for the channel at the correct position.
           */
          int value = (int) (m_pcmInfo[0][i][m_pcmIndex[i] + j] * 32767);

          /*
           * We make sure our value doesn't exceed or falls below +-32767.
           */
          if (value > 32767) {
            value = 32767;
          }
          if (value < -32768) {
            value = -32768;
          }

          /*
           * It the value is less than zero, we bitwise-or it with 32768 (which is
           * 1000000000000000 = 10^15).
           */
          if (value < 0)
            value = value | 32768;

          /*
           * Take our value and split it into two, one with the last byte and one with the
           * first byte.
           */
          m_convertedBuffer[sampleIndex] = (byte) (value);
          m_convertedBuffer[sampleIndex + 1] = (byte) (value >>> 8);

          /*
           * Move the sample index forward by two (since that's how many values we get at
           * once) times the number of channels.
           */
          sampleIndex += 2 * (m_jorbisInfo.channels);
        }
      }

      // Write the buffer to the audio output line.
      m_outputLine.write(m_convertedBuffer, 0, 2 * m_jorbisInfo.channels * range);

      // Update the DspState object.
      m_jorbisDspState.synthesis_read(range);
    }
  }

  /**
   * This method is being called internally to output debug information whenever
   * that is wanted.
   * 
   * @param output the debug output information
   */
  private void debugOutput(String output) {
    if (m_debugMode)
      System.out.println("OggPlayer: " + output);
  }

  private void debugError(String output, Throwable reason) {
    if (reason != null)
      reason.printStackTrace(System.err);
    System.err.println("OggPlayer: " + output);
  }

}
