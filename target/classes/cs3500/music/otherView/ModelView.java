package cs3500.music.otherView;

import java.awt.*;
import java.util.Collection;

import cs3500.music.otherModel.ANote;
import cs3500.music.otherModel.Note;
import cs3500.music.otherModel.OtherMusicEditorModel;
import cs3500.music.otherModel.Pitch;

/**
 * Represents a Model to View Adapter (Allows for the encapsulation of model data to protect it from
 * meddling clients
 */
public final class ModelView {

  private OtherMusicEditorModel model;

  public ModelView(OtherMusicEditorModel m) {
    this.model = m;
  }

  /**
   * Converts the MusicEditorModel to a GuiView
   *
   * @return a GuiView of the model
   */
  public OtherGuiViewFrame createGuiView() {
    return new OtherGuiViewFrame(this);
  }

  /**
   * Converts the MusicEditorModel to a MidiViewImpl
   *
   * @return a MidiViewImpl of the model
   */
  public OtherMidiViewImpl createMidiView() {
    return new OtherMidiViewImpl(this);
  }

  /**
   * Gets all notes at a given beat
   *
   * @param beat represents the beat at which we pull our notes
   * @return All notes at the given beat
   */
  public Collection<ANote> getAtBeat(int beat) {
    return this.model.notesAtBeat(beat);
  }

  /**
   * The highest note
   *
   * @return the highest note in the model
   */
  public ANote getHighest() {
    return this.model.getHighestNote();
  }

  /**
   * The lowest note
   *
   * @return the lowest note in the model
   */
  public ANote getLowest() {
    return this.model.getLowestNote();
  }

  /**
   * The range of notes or distance between highest and lowest notes
   *
   * @return the range of the model
   */
  public int getRange() {
    return this.model.getHighestNote().intValue() -
        this.model.getLowestNote().intValue();
  }

  /**
   * The last beat
   *
   * @return the last beat of the model
   */
  public int getLastBeat() {
    return this.model.lastBeat();
  }

  /**
   * Returns the number of notes in the model
   *
   * @return the number of notes in the model
   */
  public int numNote() {
    return model.numNote();
  }

  /**
   * Returns all notes in piece
   */
  public Collection<ANote> allNotes() {
    return model.allNotes();
  }

  /**
   * Returns the tempo of the model
   *
   * @return the tempo of the model
   */
  public int tempo() {
    return model.getTempo();
  }

  /**
   * Adds a note to the view's model
   *
   * @param p1 represents the start point, where the mouse is clicked
   * @param p2 represents the end point, where the mouse is released
   */
  //TODO FIX THIS ADD NOTE METHOD !!
  public void addNote(Point p1, Point p2) {

    ANote n = this.fromPoints(p1, p2);

    if (p1.equals(p2)) {
      n = this.fromPoint(p1);
    }
    model.addNote(n);

  }

  public void removeNote(Point p, Point p2) {

    ANote n = this.fromPoints(p, p2);
    if (p.equals(p2)) {
      n = this.fromPoint(p);
    }
    model.removeNote(n);

  }

  /**
   * Allows the user to move the Note given two mouse locations
   *
   * @param p1 represents the Note's current location
   * @param p2 represents the desired new location of the Note
   */
  public void movePitch(Point p1, Point p2) {
    Pitch pitch = Pitch.getPitchFromY(p1.y, this.getLowest());
    Pitch newPitch = Pitch.getPitchFromY(p2.y, this.getLowest());
    int start = (p1.x - OtherConcreteGuiViewPanel.leftOffset) / OtherConcreteGuiViewPanel.rectSize;
    int octave = this.getOctaveFromY(p1.y, this.getLowest());
    int newOctave = this.getOctaveFromY(p2.y, this.getLowest());
    ANote n = model.getNote(start, pitch, octave);
    model.movePitch(n, newPitch, newOctave);
  }

  /**
   * Allows the user to move the Note given two mouse locations
   *
   * @param p1 represents the Note's current location
   * @param p2 represents the desired new location of the Note
   */
  public void moveBeat(Point p1, Point p2) {
    Pitch pitch = Pitch.getPitchFromY(p1.y, this.getLowest());
    int start = (p1.x - OtherConcreteGuiViewPanel.leftOffset) / OtherConcreteGuiViewPanel.rectSize;
    int newStart = (p2.x - OtherConcreteGuiViewPanel.leftOffset) / OtherConcreteGuiViewPanel.rectSize;
    int octave = this.getOctaveFromY(p1.y, this.getLowest());
    ANote n = model.getNote(start, pitch, octave);
    int range = n.getFinish() - n.getStart();
    model.moveBeat(n, newStart, newStart + range);
  }

  /**
   * Adds 10 on to the total number of beats in the model
   */
  public void addBeats() {
    model.addBeats(10);
  }

  /**
   * Determines an octave value from a given Y-coordinated
   *
   * @param Y   represents the Y-coordinate of a mouse click
   * @param low represents the low Note of the given editor
   * @return an int representing the octave at the given location
   */
  private int getOctaveFromY(int Y, ANote low) {
    int PitchVal = low.intValue() +
        (OtherConcreteGuiViewPanel.bottom - Y) / OtherConcreteGuiViewPanel.rectSize;

    return PitchVal / 11;

  }

  /**
   * Creates a Note from a given mouse Points
   *
   * @param p  represents the mouse's position
   * @param p2 represents the mouse's location on release (end point)
   * @return a Note representing the location
   */
  private ANote fromPoints(Point p, Point p2) {
    Pitch pitch = Pitch.getPitchFromY(p.y, this.getLowest());
    int start = (p.x - OtherConcreteGuiViewPanel.leftOffset) / OtherConcreteGuiViewPanel.rectSize;
    int end = (p2.x - OtherConcreteGuiViewPanel.leftOffset) / OtherConcreteGuiViewPanel.rectSize;
    int octave = this.getOctaveFromY(p.y, this.getLowest());
    Note n = Note.makeNote(pitch, start, end, octave, 64, 1);
    return n;
  }


  /**
   * Creates a Note from a given mouse Points
   *
   * @param p represents the mouse's position
   * @return a Note representing the location
   */
  private ANote fromPoint(Point p) {
    Pitch pitch = Pitch.getPitchFromY(p.y, this.getLowest());
    int start = (p.x - OtherConcreteGuiViewPanel.leftOffset) / OtherConcreteGuiViewPanel.rectSize;
    int octave = this.getOctaveFromY(p.y, this.getLowest());
    Note n = Note.makeNote(pitch, start, start + 1, octave, 64, 1);
    return n;
  }

}