package cs3500.music.otherView;

import java.awt.*;

import javax.swing.*;

import cs3500.music.otherModel.ANote;
import cs3500.music.otherModel.Pitch;

/**
 * Represents the Gui View Panel that represents the entire music editor
 */
public class OtherConcreteGuiViewPanel extends JPanel {

  // The highest Note in the given piece
  private static ANote highNote;
  // The Lowest note in the given piece
  private static ANote lowNote;
  // The total range of the piece (# of pitches)
  protected static int range;
  // Total number of beats in the piece
  protected static int beats;
  private ModelView model;
  // Increased range of Pitches (if desired by client)
  public static int addPitch = 0;
  // Dimensions for Rectangle + Spacing multiple for Notes
  public static final int rectSize = 20;
  // Amount to offset notes left to account for Pitches
  public static int leftOffset = 30;
  // Amount to offset Notes down to account for Beat nums
  public static final int topOffset = 15;
  // Represents the bottom coordinate of the editor
  public static int bottom = topOffset + (range * rectSize);
  // Represents the current color used when drawing sustains
  public static Color sustainColor = Color.YELLOW;
  // Represents the current beat of the midi (useful for the red line)
  private static int curBeat = 0;
  // Represents the number of visible beats on the screen
  private static final int visibleBeats = 64;

  OtherConcreteGuiViewPanel(ModelView m) {

    this.model = m;
    this.highNote = m.getHighest();
    this.lowNote = m.getLowest();
    this.beats = m.getLastBeat();
    this.range = m.getRange();
    bottom = topOffset + (this.range * rectSize);

  }

  /**
   * Draws the music editor model
   *
   * @param g represents the graphics to be outputted
   */
  @Override
  public void paint(Graphics g) {

    this.beats = model.getLastBeat();

    super.paint(g);
    //setSize(1080, 900);

    // Represents a String's Y coordinate; will be updated by for loop
    int stringY = 1;

    // Draws a line to separate Pitches from Notes
    g.drawLine(leftOffset, topOffset, leftOffset, bottom);

    // Draws all the Notes
    for (ANote n : model.allNotes()) {

      g.setColor(Color.black);
      g.fillRect((n.getStart() * rectSize) + leftOffset,
          bottom - ((n.intValue() - lowNote.intValue()) * rectSize),
          rectSize, rectSize);

      for (int x = n.getStart() + 1; x < n.getFinish(); x++) {
        g.setColor(sustainColor);
        g.fillRect((x * rectSize) + leftOffset,
            bottom - ((n.intValue() - lowNote.intValue()) * rectSize),
            rectSize, rectSize);
      }
    }

    // Adds all necessary Pitch Strings
    for (int i = highNote.intValue() + addPitch; i >= lowNote.intValue() - addPitch; i--) {

      String s = Pitch.pitchToString(i);
      g.setColor(Color.black);
      // Draws Strings between Pitches
      g.drawString(s, 0, topOffset + (stringY * rectSize));

      // Draws lines for each pitch shown
      g.drawLine(leftOffset,
          bottom + (addPitch * 2 * rectSize) - (((stringY - 2) * rectSize)),
          (visibleBeats + 2) * rectSize,
          bottom + (addPitch * 2 * rectSize) - ((stringY - 2) * rectSize));

      // Adds to stringY value to move next lines down
      stringY = stringY + 1;
    }

    // Adds all beat ints (on top)
    for (int i = 0; i < beats; i = i + 4) {

      String s = Integer.toString(i);
      g.setColor(Color.black);
      // Draws Strings for beat values
      g.drawString(s, (i * rectSize) + leftOffset, 10);
      g.drawLine((i * rectSize) + leftOffset, topOffset,
          (i * rectSize) + leftOffset, bottom + rectSize + (addPitch * rectSize));
    }

    // Draws the moving red line
    g.setColor(Color.red);
    g.drawLine(leftOffset + (curBeat * rectSize), 0,
        leftOffset + (curBeat * rectSize), bottom + rectSize);

    this.repaint();
  }

  // Overridden to allow for Scrolling
  @Override
  public Dimension getPreferredSize() {
    return new Dimension((beats + 5) * rectSize, bottom + rectSize);
  }

  /**
   * Converts the Note sustain color to Cyan blue
   */
  public void changeBlue() {
    sustainColor = Color.CYAN;
    this.repaint();
  }

  /**
   * Converts the Note sustain color to Cyan blue
   */
  public void changeYellow() {
    sustainColor = Color.YELLOW;
    repaint();
  }

  public void tickBeat() {
    this.curBeat += 1;
    if (curBeat % visibleBeats == 0) {
      leftOffset -= (visibleBeats * rectSize);
    }
    repaint();
  }

  /**
   * Adds pitches to the top and bottom of the piece (render)
   *
   * @param add represents the number of pitches to add
   */
  public void addPitchs(int add) {
    addPitch += add;
    bottom += (add * rectSize);
  }

  /**
   * Method to let the user scroll the board to the right
   */
  public void scrollRight() {
    leftOffset -= 10;
    System.out.println(leftOffset);
  }

  /**
   * Method to let the user scroll the board to the left
   */
  public void scrollLeft() {

    if (leftOffset <= 15) {
      leftOffset += 10;
    }
  }

  /**
   * Goes to the beginning of the piece
   */
  public void toHome() {
    leftOffset = 15;
    this.repaint();
  }

  /**
   * Goes to the end of the piece and renders it
   */
  public void toEnd() {
    leftOffset = leftOffset - (beats * rectSize) + (visibleBeats * rectSize);
    this.repaint();
  }

  public int getClickedPitch(int y) {

    int PitchVal = model.getLowest().intValue() +
        (bottom - y) / rectSize;

    PitchVal = PitchVal + PitchVal / 11 + 1;

    return PitchVal;
  }

  public int getClickedBeat(int x) {
    int beat = (x - leftOffset) / rectSize;
    return beat;
  }

}