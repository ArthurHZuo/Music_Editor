package cs3500.music.util;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;

import cs3500.music.model.MusicEditorViewModel;
import cs3500.music.view.MusicEditorGuiView;

/**
 * View to be able to check if handlers work as intended.
 */
public class MockGuiView implements MusicEditorGuiView {

  private MouseListener mouse;
  private KeyListener key;

  public MockGuiView() {

  }

  @Override
  public void addKeyListener(KeyListener kl) {
    this.key = kl;
  }

  @Override
  public void addMouseListener(MouseListener ml) {
    this.mouse = ml;
  }

  @Override
  public int getClickedPitch(int yCoord) {
    return 24 + yCoord;
  }

  @Override
  public int getClickedBeat(int xCoord) {
    return xCoord;
  }

  @Override
  public void reactToDirectionalKey(boolean moveUp) {

  }

  @Override
  public void reactCompletelyToDirectionalKey(boolean moveUp) {

  }

  @Override
  public void view(MusicEditorViewModel vm) throws IOException {

  }

  public KeyListener getKeyHandler() {
    return this.key;
  }

  public MouseListener getMouseHandler() {
    return this.mouse;
  }

}
