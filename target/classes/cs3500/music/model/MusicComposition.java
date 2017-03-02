package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of MusicEditorModel.
 */

final class MusicComposition implements MusicEditorControllerModel {

  /**
   * INVARIANT: {@code notes} is always sorted by the starting beat of a note
   */
  private final ArrayList<NoteModel> notes;
  private final int tempo;
  private int currentBeat;
  private boolean playing;

  /**
   * Constructor for MusicComposition where it sets the ArrayList of NoteModel to be empty.
   *
   * @param notes the list of notes that are in the composition
   * @param tempo the tempo of the piece.
   */
  private MusicComposition(ArrayList<NoteModel> notes, int tempo) {
    this.notes = notes;
    this.tempo = tempo;
    this.currentBeat = 0;
    this.playing = false;
  }

  /**
   * Static factory constructor for a {@code MusicComposition} with no {@code NoteModel}s in it.
   *
   * @param tempo the tempo of the piece.
   * @return An empty {@code MusicEditorModel}.
   */
  public static MusicComposition emptyEditor(int tempo) {
    return new MusicComposition(new ArrayList<>(), tempo);
  }

  /**
   * Static factory constructor for a {@code MusicComposition} with the given {@code NoteModel}s in
   * it.
   *
   * @param tempo the tempo of the piece.
   * @param notes the initial NoteModels to add to the composition.
   * @return A {@code MusicEditorModel} containing the given {@code NoteModel}s.
   * @throws IllegalArgumentException if any of the given {@code NoteModel}s are null.
   */
  public static MusicComposition populatedEditor(int tempo, NoteModel... notes) {
    MusicComposition tempEditor = MusicComposition.emptyEditor(tempo);
    for (NoteModel note : notes) {
      tempEditor.addNote(note);
    }
    return tempEditor;
  }

  @Override
  public boolean isPlaying() {
    return this.playing;
  }

  @Override
  public void togglePlaying() {
    this.playing = !this.playing;
  }

  @Override
  public void addNote(NoteModel note) {
    boolean added = false;
    if (note == null) {
      throw new IllegalArgumentException("note cannot be null");
    }
    for (int i = this.notes.size() - 1; i > 0; i--) {
      if (this.notes.get(i).getBeat() <= note.getBeat() && !added) {
        this.notes.add(i + 1, note);
        added = true;
      }
    }
    if (!added) {
      this.notes.add(0, note);
    }
  }


  @Override
  public void removeNote(NoteModel note) {
    boolean removed = false;
    if (note == null) {
      throw new IllegalArgumentException("note cannot be null");
    }
    for (int i = this.notes.size() - 1; i >= 0; i--) {
      if (this.notes.get(i).equals(note) && !removed) {
        this.notes.remove(i);
        removed = true;
      }
    }
    if (!removed) {
      throw new IllegalArgumentException("note is not in the editor");
    }
  }

  @Override
  public void editNote(NoteModel oldNote, NoteModel newNote) {
    this.removeNote(oldNote);
    this.addNote(newNote);
  }

  @Override
  public List<NoteModel> getNotesPlayingAt(int beat) {
    ArrayList<NoteModel> out = new ArrayList<>();
    if (beat < 0) {
      throw new IllegalArgumentException("beat must be greater than 0");
    }
    for (NoteModel note : this.notes) {
      if (note.getBeat() <= beat && note.getBeat() + (note.getDuration() - 1) >= beat) {
        out.add(note);
      }
    }
    return out;
  }

  @Override
  public List<NoteModel> getNotesStartingAt(int beat) {
    ArrayList<NoteModel> out = new ArrayList<>();
    if (beat < 0) {
      throw new IllegalArgumentException("beat must be greater than 0");
    }
    for (NoteModel note : this.notes) {
      if (note.getBeat() == beat) {
        out.add(note);
      }
    }
    return out;
  }

  @Override
  public int lastBeat() {
    int max = 0;
    for (NoteModel note : this.notes) {
      if (note.getBeat() + note.getDuration() > max) {
        max = note.getBeat() + note.getDuration() - 1;
      }
    }
    return max;
  }

  @Override
  public NoteModel lowestNote() {
    if (this.notes.size() == 0) {
      throw new IllegalStateException("No Notes in Editor");
    }
    NoteModel lowest = this.notes.get(0);
    for (NoteModel note : this.notes) {
      if (note.comparePitch(lowest) > 0) {
        lowest = note;
      }
    }
    return lowest;
  }

  @Override
  public NoteModel highestNote() {
    if (this.notes.size() == 0) {
      throw new IllegalStateException("No Notes in Editor");
    }
    NoteModel highest = this.notes.get(0);
    for (NoteModel note : this.notes) {
      if (note.comparePitch(highest) < 0) {
        highest = note;
      }
    }
    return highest;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public int getCurrentBeat() {
    return this.currentBeat;
  }

  @Override
  public void setCurrentBeat(int beat) {
    if (beat < 0) {
      throw new IllegalArgumentException("Arguments must be valid");
    }
    this.currentBeat = beat;
  }
}
