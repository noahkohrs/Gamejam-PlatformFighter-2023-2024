/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 * 
 * This work is based on the JOrbis-0.0.17
 *   Copyright (C) 2000 ymnk, JCraft,Inc.
 *   Written by: 2000 ymnk<ymnk@jcraft.com>
 *   Many thanks to 
 *     Monty <monty@xiph.org> and 
 *     The XIPHOPHORUS Company http://www.xiph.org/ .
 *     JOrbis has been based on their awesome works, Vorbis codec and
 *     JOrbisPlayer depends on JOrbis.
 *     
 *  Derived work, March, 2020
 *  Author: Pr. Olivier Gruber
 */
package info3.game.sound;

import java.io.InputStream;

import info3.game.graphics.GameCanvas;

/* 
 * A player for audio files in the ogg format.
 * 
 * Nota Bene:
 *   The code for playing the audio stream is not pretty 
 *   and certainly not a good example of how to code in Java.
 * 
 * This player is created by the game canvas and disposed
 * of when the window containing the canvas is closed,
 * When disposed of, the player stops the playing of 
 * the current sound, if any.
 * 
 */
public abstract class AudioPlayer {

  /* 
   * Constant to control if the player is verbose in the console
   * about what it plays.
   */
  protected static final boolean VERBOSE = false;
  
  /* 
   * Constant to control if the player is verbose about possible
   * errors encountered while playing audio.
   */
  protected static final boolean VERBOSE_ERROR = false;

  protected AudioPlayerListener m_listener;
  
  protected GameCanvas m_canvas;
  
  protected AudioPlayer(GameCanvas canvas) {
    m_canvas = canvas;
  }
  
  public abstract String getName();
  
  /*
   * Ask the player to stop playing the current audio stream
   * as soon as possible.
   */
  public abstract void stop();

  /*
   * Ask the player to play the given audio stream as sound effect, 
   * as soon as possible, over the playing background music, if any.
   * If a sound with the same name is already playing, it is interrupted
   * to play the new sound. 
   */
  public abstract void playSound(String name, final InputStream is, long duration, float volume, AudioPlayerListener l);

  /*
   * Ask the player to play the given audio stream, as the background music.
   * If a music is already playing, it is interrupt and the new music starts playing.
   */
  public abstract void playMusic(String name, final InputStream is, long duration, float volume);

}
