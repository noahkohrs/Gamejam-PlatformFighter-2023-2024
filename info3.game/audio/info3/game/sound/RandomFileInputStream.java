package info3.game.sound;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class RandomFileInputStream extends InputStream {

  public static void main(String[] args) throws IOException {
    String filename="/homex/ogruber/Workshops/2019/workshops/pla/prof.repo/README.md";
    RandomAccessFile file = new RandomAccessFile (filename,"r");
    RandomFileInputStream fis = new RandomFileInputStream(file);
    fis.mark(Integer.MAX_VALUE);
    int count=0;
    for(;;) {
      int bits = fis.read();
      if (bits==-1)
        break;
      System.out.print(Integer.toString(bits)+" ");
      if (count++==16) {
        System.out.println();
        count=0;
      }
    }
    System.out.println();
    fis.reset();
    for(;;) {
      int bits = fis.read();
      if (bits==-1)
        break;
      System.out.print(Integer.toString(bits)+" ");
      if (count++==16) {
        System.out.println();
        count=0;
      }
    }
    System.out.println();
  }
  
  private RandomAccessFile raf;
  private long mark = 0;

  public RandomFileInputStream(RandomAccessFile raf) throws IOException {
    this.raf = raf;
    raf.seek(0);
  }

  @Override
  public int available() throws IOException {
    return (int)(raf.length()-raf.getFilePointer());
  }

  @Override
  public void mark(int readlimit) {
    try {
      mark = raf.getFilePointer();
    } catch (IOException e) {
      // e.printStackTrace();
    }
  }

  @Override
  public boolean markSupported() {
    return true;
  }

  @Override
  public void reset() throws IOException {
    raf.seek(mark);
    assert(mark == raf.getFilePointer());
  }

  @Override
  public long skip(long n) throws IOException {
    if (n < 0)
      return 0;
    long p = raf.getFilePointer();
    raf.seek(p + n);
    return raf.getFilePointer()-p;
  }

  @Override
  public void close() throws IOException {
    raf.close();
    super.close();
  }

  @Override
  public int read() throws IOException {
    return raf.read();
  }
}