package cs3500.music.util;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Objects;

import cs3500.music.model.MusicEditorViewModel;
import cs3500.music.otherView.OtherGuiView;
import cs3500.music.view.MusicEditorGuiView;

/**
 * Adapts the foreign GuiView to our GuiView to work with our controller and it's
 * architecture.
 */
public class AdaptOtherGuiViewtoOurGuiView implements MusicEditorGuiView, OtherGuiView {
  private final OtherGuiView view;

  /**
   * Constructs an instance of the adapter.
   * @param view the view to adapt to our view
   * @throws IllegalArgumentException if the view passed in is null.
   */
  public AdaptOtherGuiViewtoOurGuiView(OtherGuiView view) {
    Objects.requireNonNull(view);
    this.view = view;
  }

  @Override
  public void addKeyListener(KeyListener kl) {
    this.view.addKeyListener(kl);
  }

  @Override
  public void addMouseListener(MouseListener ml) {
    this.view.addMouseListener(ml);
  }

  @Override
  public int getClickedPitch(int yCoord) {
    return this.view.getClickedPitch(yCoord);
  }

  @Override
  public int getClickedBeat(int xCoord) {
    return this.view.getClickedBeat(xCoord);
  }
  @Override
  public void reactToDirectionalKey(boolean moveUp) {
    //They do not implement this functionality, so we leave this blank.
  }

  @Override
  public void reactCompletelyToDirectionalKey(boolean moveUp) {
    //They do not implement this functionality, so we leave this blank.
  }

  @Override
  public void view(MusicEditorViewModel vm) throws IOException {
    this.view.render();
  }

  @Override
  public void changeBlue() {
    this.view.changeBlue();
  }

  @Override
  public void changeYellow() {
    this.view.changeYellow();
  }

  @Override
  public void toEnd() {
    this.view.toEnd();
  }

  @Override
  public void tickBeat() {
    this.view.tickBeat();
  }

  @Override
  public void addPitchs() {
    this.view.addPitchs();
  }

  @Override
  public void scrollRight() {
    this.view.scrollRight();
  }

  @Override
  public void scrollLeft() {
    this.view.scrollLeft();
  }

  @Override
  public void render() {
    this.view.render();
  }
}
