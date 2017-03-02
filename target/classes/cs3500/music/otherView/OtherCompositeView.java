package cs3500.music.otherView;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;


/**
 * A combined view showing both the gui and playing music
 */
public class OtherCompositeView implements OtherMusicEditorView, OtherGuiView {

  private ModelView m;
  private OtherGuiViewFrame view;
  private OtherMidiViewImpl midi;
  private String lastKey;
  private String lastMouse;

  public OtherCompositeView(ModelView m) {

    view = new OtherGuiViewFrame(m);
    midi = new OtherMidiViewImpl(m);
    this.m = m;

  }

  @Override
  public void render() {
    view.render();
  }

  @Override
  public void addKeyListener(KeyListener k) {
    view.addKeyListener(k);
    midi.addKeyListener(k);
  }

  /**
   * Adds a mouseListener to the view
   *
   * @param ml represents the mouseListener to be added
   */
  public void addMouseListener(MouseListener ml) {
    view.addMouseListener(ml);
  }


  /**
   * Changes the colors of the sustains on the board to blue
   */
  public void changeBlue() {
    view.changeBlue();
    this.repaint();
  }

  /**
   * Changes the colors of the note sustains to yellow
   */
  public void changeYellow() {
    view.changeYellow();
  }

  /**
   * Moves the render piece back to the beginning
   */
  public void toBeginning() {
    view.toBeginning();
  }

  /**
   * Moves the render piece to the end
   */
  public void toEnd() {
    view.toEnd();
  }


  /**
   * Begins Music Playback
   */
  public void startMidi() {
    midi.render();
  }

  /**
   * Repaints the gui view
   */
  public void repaint() {
    view.repaint();
  }

  /**
   * Adds a new note to the model based on two given points
   *
   * @param p1 represents the start point of the note
   * @param p2 represents the end point of the note
   */
  public void addNote(Point p1, Point p2) {
    m.addNote(p1, p2);
  }

  /**
   * Removes a new note to the model based on two given points
   *
   * @param p  represents the start point of the note
   * @param p2 represents the end point of the note
   */
  public void removeNote(Point p, Point p2) {
    m.removeNote(p, p2);
  }

  /**
   * Ticks the model forward by one beat (moves the red line/ render forward)
   */
  public void tickBeat() {
    view.tickBeat();
  }

  /**
   * Adds 10 beats to the end of the model piece
   */
  public void addBeats() {
    m.addBeats();
    view.repaint();
  }

  /**
   * Moves a note to the desired pitch
   *
   * @param p1 location of original note
   * @param p2 location of notes desired pitch
   */
  public void movePitch(Point p1, Point p2) {
    m.movePitch(p1, p2);
  }

  /**
   * Moves a note to the desired beat
   *
   * @param p1 location of original note
   * @param p2 location of notes desired beat
   */
  public void moveBeat(Point p1, Point p2) {
    m.moveBeat(p1, p2);
  }

  /**
   * Adds a pitch to the top and bottom of the given piece
   */
  public void addPitchs() {
    view.addPitchs();
  }

  /**
   * Scrolls the view to the left
   */
  public void scrollLeft() {
    view.scrollLeft();
  }

  @Override
  public int getClickedPitch(int yCoord) {
    return view.getClickedPitch(yCoord);
  }

  @Override
  public int getClickedBeat(int xCoord) {
    return view.getClickedBeat(xCoord);
  }

  /**
   * Scrolls the piece to the right
   */
  public void scrollRight() {
    view.scrollRight();
  }

  public String getLastKey() {
    return this.lastKey;
  }

  public String getLastMouse() {
    return this.lastMouse;
  }

  public void changeBlueMock() {
    this.lastKey = "B pressed";
  }

  public void changeYellowMock() {
    this.lastKey = "Y pressed";
  }

  public void toBeginningMock() {
    this.lastKey = "Home pressed";
  }

  public void toEndMock() {
    this.lastKey = "End pressed";
  }

  public void addPitchsMock() {
    this.lastKey = "2 pressed";
  }

  public void scrollLeftMock() {
    this.lastKey = "Left pressed";
  }

  public void scrollRightMock() {
    this.lastKey = "Right pressed";
  }

  public void repaintMock() {
    this.lastKey = "B Released";
  }

  /**
   *
   */
  public void addNoteMock(Point p1, Point p2) {
    this.lastMouse = "left click at: " + p1.getX() + ", " + p1.getY() + "\n" +
        "release at: " + p2.getX() + ", " + p2.getY();
  }

  public void removeNoteMock(Point p1, Point p2) {
    this.lastMouse = "right click at: " + p1.getX() + ", " + p1.getY() + "\n" +
        "release at: " + p2.getX() + ", " + p2.getY();
  }

}