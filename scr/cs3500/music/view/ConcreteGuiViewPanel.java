package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.music.model.MusicEditorViewModel;
import cs3500.music.model.NoteModel;
import cs3500.music.model.Pitch;

/**
 * A visual view that uses Swing to draw a grid onto a blank surface and draws the notes that are
 * playing and starting in each position.
 */
public class ConcreteGuiViewPanel extends JPanel {
  private final int SQUARE_SIZE = 20;
  private final int BOUNDARY_SIZE = 40;
  MusicEditorViewModel modelToRender;
  private int beatLength;
  private int octaveRange;
  private int highOctave;

  public ConcreteGuiViewPanel(MusicEditorViewModel mevo) {
    this.modelToRender = mevo;
  }

  /**
   * Returns a {@link ArrayList} that lists the range of all of the {@link NoteModel}s pitches in
   * {@code model}* Represents the sidebar of the gui view with the  pitch/ocatve that is
   * represented by each line in the visual view.
   *
   * @param lowOctave  the lowest octave of the piece.
   * @param highOctave the highest octave of the piece.
   * @return A {@link ArrayList} that represents the range of all of the the {@link NoteModel}s
   * pitches in {@code model}.
   */
  private static ArrayList<String> getHeader(int lowOctave, int highOctave) {
    ArrayList<String> out = new ArrayList<>();

    for (int i = lowOctave; i <= highOctave; i++) {
      for (Pitch p : Pitch.values()) {
        out.add(p.toString() + i);
      }
    }
    return out;
  }

  /**
   * Returns the color that the note should be rendered as based on the instrument that the note
   * represents. Each family of instruments(Piano, percussion, organ, etc.), has it's own color.
   * COLOR SCHEME Piano Family -- Green Percussion Family -- Blue Organ Family -- Red Guitar Family
   * -- Cyan Bass Family -- Light Gray Strings Family -- Yellow Ensemble Family
   * -- Pink Brass Family
   * -- Orange Everything else(Reed, Pipe, and Electronic Synth.) -- Dark Gray
   *
   * @param instrument the MIDI instrument number of this particular note.
   * @return the color corresponding with this note.
   */
  private static Color getColor(int instrument) {
    instrument = instrument / 8 + 1;
    switch (instrument) {
      case 1:
        return Color.GREEN;
      case 2:
        return Color.BLUE;
      case 3:
        return Color.RED;
      case 4:
        return Color.CYAN;
      case 5:
        return Color.LIGHT_GRAY;
      case 6:
        return Color.YELLOW;
      case 7:
        return Color.PINK;
      case 8:
        return Color.ORANGE;
      default:
        return Color.DARK_GRAY;
    }
  }

  /**
   * Given an octave and a pitch, calculate the Y coordinate on which to place the top left corner
   * of the rectangle.
   *
   * @param octave the octave of the note to be rendered
   * @param pitch  the pitch of the note to be rendered
   * @return the Y coordinate where the top left corner of the note should be placed
   */

  private int calcYCoordinate(int octave, Pitch pitch) {
    int yCoord = BOUNDARY_SIZE;
    int octaveOffset = this.highOctave - octave + 1;
    yCoord = yCoord + (octaveOffset * 12 * SQUARE_SIZE);
    yCoord = yCoord + (11 - pitch.getMidiPitch(0)) * SQUARE_SIZE;
    return yCoord;
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(this.beatLength * SQUARE_SIZE + BOUNDARY_SIZE,
        this.octaveRange * SQUARE_SIZE * 12 + BOUNDARY_SIZE);
  }

  @Override
  public void paintComponent(Graphics g) {
    //Assigns variables to use in the drawing -- borders and ranges etc.
    int width;
    int currentBeat;
    int leftSide;
    int rightSide;
    width = (int) this.getMaximumSize().getWidth();
    currentBeat = this.modelToRender.getCurrentBeat();
    leftSide = currentBeat - currentBeat % ((width - BOUNDARY_SIZE) / SQUARE_SIZE);
    rightSide = leftSide + (width - BOUNDARY_SIZE) / SQUARE_SIZE;
    int lowOctave;
    if (this.modelToRender.lastBeat() > 0) {
      this.beatLength = modelToRender.lastBeat();
      this.octaveRange =
          modelToRender.highestNote().getOctave() - modelToRender.lowestNote().getOctave() + 1;
      lowOctave = modelToRender.lowestNote().getOctave();
      this.highOctave = modelToRender.highestNote().getOctave();
    } else {
      this.beatLength = modelToRender.lastBeat();
      this.octaveRange = 8;
      lowOctave = 0;
      this.highOctave = 7;
    }

    //gives you a blank canvas
    super.paintComponent(g);

    //draw all notes playing at any given point and that fall inside the specified
    // octaves for starting and ending range -- represent as squares. Color depends on the
    //instrument being played -- see getColor above for specifics.
    for (int i = leftSide; i <= rightSide; i++) {
      List<NoteModel> notesAti = modelToRender.getNotesPlayingAt(i);
      for (NoteModel n : notesAti) {
        if (n.getOctave() >= lowOctave && n.getOctave() <= this.highOctave) {
          g.setColor(getColor(n.getInstrument()));
          g.drawRect((i - leftSide) * SQUARE_SIZE + BOUNDARY_SIZE,
              this.calcYCoordinate(n.getOctave(), n.getPitch()),
              SQUARE_SIZE, SQUARE_SIZE);
          g.fillRect((i - leftSide) * SQUARE_SIZE + BOUNDARY_SIZE + 1,
              this.calcYCoordinate(n.getOctave(), n.getPitch()),
              SQUARE_SIZE, SQUARE_SIZE);
        }
      }
    }
    //draw all notes starting at any given point and that fall inside the specified
    // octaves for starting and ending range -- will overwrite the notes that are
    //playing to show the notes that are starting as starting should be shown as it's own
    //color(BLACK) -- takes precedence.
    g.setColor(Color.BLACK);
    for (int i = leftSide; i <= rightSide; i++) {
      List<NoteModel> notesStartAtI = modelToRender.getNotesStartingAt(i);
      for (NoteModel n : notesStartAtI) {
        if (n.getOctave() >= lowOctave && n.getOctave() <= this.highOctave) {
          g.drawRect((i - leftSide) * SQUARE_SIZE + BOUNDARY_SIZE,
              this.calcYCoordinate(n.getOctave(), n.getPitch()),
              SQUARE_SIZE, SQUARE_SIZE);
          g.fillRect((i - leftSide) * SQUARE_SIZE + BOUNDARY_SIZE,
              this.calcYCoordinate(n.getOctave(), n.getPitch()),
              SQUARE_SIZE, SQUARE_SIZE);
        }
      }
    }

    //The last beat of the song, rounded up to the next measure. This is used for an accurate and
    //smooth rendering as opposed to an abrupt ending.
    int pitchMeasure = rightSide;
    pitchMeasure = pitchMeasure - (pitchMeasure % 4) + 4;

    //Draw horizontal lines to represent each pitch.
    for (int i = 0; i <= this.octaveRange * 12; i++) {
      g.drawLine(BOUNDARY_SIZE, (i * SQUARE_SIZE + BOUNDARY_SIZE),
          ((pitchMeasure - leftSide) * SQUARE_SIZE + BOUNDARY_SIZE),
          (i * SQUARE_SIZE + BOUNDARY_SIZE));
    }
    //Draw vertical lines at every 4th beat to represent a measure, up to the length of the piece,
    //rounded up to the next measure.
    for (int i = leftSide; i <= pitchMeasure; i++) {
      if (i % 4 == 0) {
        g.drawLine((i - leftSide) * SQUARE_SIZE + BOUNDARY_SIZE, BOUNDARY_SIZE,
            (i - leftSide) * SQUARE_SIZE + BOUNDARY_SIZE,
            this.octaveRange * SQUARE_SIZE * 12 + BOUNDARY_SIZE);
      }
    }

    //Draw the beat numbers at the top of the display.
    for (int i = leftSide; i <= pitchMeasure; i++) {
      if (i % 4 == 0) {
        g.drawString("" + i, (i - leftSide) * SQUARE_SIZE + BOUNDARY_SIZE - 5, BOUNDARY_SIZE - 5);
      }
    }

    //An arrayList of the pitches to add to the model, as strings.
    ArrayList<String> pitches = getHeader(lowOctave, this.highOctave);
    //Draw the octave numbers and pitches .
    for (int i = pitches.size() - 1; i >= 0; i--) {
      g.drawString(pitches.get(i), 5,
          (pitches.size() - i) * SQUARE_SIZE + SQUARE_SIZE + BOUNDARY_SIZE / 2 - 5);
    }
    //Draw the red bar representing the current beat being played.
    g.setColor(Color.RED);
    g.drawLine((currentBeat - leftSide) * SQUARE_SIZE + BOUNDARY_SIZE, BOUNDARY_SIZE,
        (currentBeat - leftSide) * SQUARE_SIZE + BOUNDARY_SIZE, this.getHeight());
  }
}
