package com.zeevox.recorder.audio;

/**
 * Interface for disposable resources.
 *
 * @author mzechner
 */
public interface Disposable {
  /** Releases all resources of this object. */
  public void dispose();
}
