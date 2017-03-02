package cs3500.music.otherView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import cs3500.music.model.MusicEditorControllerModel;
import cs3500.music.model.NoteBuilderImp;
import cs3500.music.model.NoteModel;
import cs3500.music.otherModel.ANote;
import cs3500.music.otherModel.OtherMusicEditorModel;
import cs3500.music.otherModel.Pitch;
import cs3500.music.util.AdaptPitch;
import cs3500.music.util.NoteAdapter;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.MusicEditorView;

/**
 * Adapter for MuscicEditorModels to OtherMusicEditorModel
 */
public class MusicEditorModelAdapter implements OtherMusicEditorModel {
  private final MusicEditorControllerModel model;

  public MusicEditorModelAdapter(MusicEditorControllerModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Arguments must be non-null");
    }
    this.model = model;
  }

  @Override
  public void addNote(ANote n) {
    this.model.addNote(NoteAdapter.adaptANote(n));
  }

  @Override
  public void removeNote(ANote n) {
    this.model.removeNote(NoteAdapter.adaptANote(n));
  }

  @Override
  public void movePitch(ANote note, Pitch pitch, int octave) {
    this.model.editNote(NoteAdapter.adaptANote(note), new NoteBuilderImp()
        .setStart(note.getStart())
        .setEnd(note.getFinish())
        .setInstrument(note.getInstrument())
        .setVolume(note.getVolume())
        .setPitch(AdaptPitch.adaptTheirPitch(pitch).getMidiPitch(octave)).build());
  }

  @Override
  public void moveBeat(ANote note, int newStart, int newEnd) {
    this.model.editNote(NoteAdapter.adaptANote(note), new NoteBuilderImp()
        .setStart(newStart)
        .setEnd(newEnd)
        .setInstrument(note.getInstrument())
        .setVolume(note.getVolume())
        .setPitch(AdaptPitch.adaptTheirPitch(
            note.getPitch()).getMidiPitch(note.getOctave())).build());
  }

  @Override
  public ANote getHighestNote() {
    return NoteAdapter.adaptNoteModel(this.model.highestNote());
  }

  @Override
  public ANote getLowestNote() {
    return NoteAdapter.adaptNoteModel(this.model.lowestNote());
  }

  @Override
  public int firstBeat() {
    return 0;
  }

  @Override
  public int lastBeat() {
    return this.model.lastBeat();
  }

  @Override
  public ANote getNote(int beat, Pitch p, int octave) {
    for (NoteModel note : this.model.getNotesStartingAt(beat)) {
      if (note.getPitch() == AdaptPitch.adaptTheirPitch(p) && octave == note.getOctave()) {
        return NoteAdapter.adaptNoteModel(note);
      }
    }
    throw new IllegalArgumentException("Note not found");
  }

  @Override
  public boolean isNoteAt(int beat, Pitch pitch, int octave) {
    for (NoteModel note : this.model.getNotesStartingAt(beat)) {
      if (note.getPitch() == AdaptPitch.adaptTheirPitch(pitch) && octave == note.getOctave()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String drawScore() {
    StringBuilder out = new StringBuilder();
    MusicEditorView view = new ConsoleView(out);
    try {
      view.view(model);
    } catch (IOException e) {
      throw new IllegalStateException("String Builder went wrong");
    }
    return out.toString();
  }

  @Override
  public Collection<ANote> notesAtBeat(int beat) {
    ArrayList<ANote> out = new ArrayList<ANote>();
    for (NoteModel note : this.model.getNotesStartingAt(beat)) {
      out.add(NoteAdapter.adaptNoteModel(note));
    }
    return out;
  }

  @Override
  public void print() {
    MusicEditorView view = new ConsoleView(System.out);
    try {
      view.view(model);
    } catch (IOException e) {
      throw new IllegalStateException("String Builder went wrong");
    }
  }

  @Override
  public int numNote() {
    int count = 0;
    for (int i = 0; i < this.model.lastBeat(); i++) {
      for (NoteModel note : this.model.getNotesStartingAt(i)) {
        count++;
      }
    }
    return count;
  }

  @Override
  public void setTempo(int t) {
    //TODO
  }

  @Override
  public Collection<ANote> allNotes() {
    ArrayList<ANote> out = new ArrayList<ANote>();
    for (int i = 0; i < this.model.lastBeat(); i++) {
      for (NoteModel note : this.model.getNotesStartingAt(i)) {
        out.add(NoteAdapter.adaptNoteModel(note));
      }
    }
    return out;
  }

  @Override
  public int getTempo() {
    return this.model.getTempo();
  }

  @Override
  public int numBeats() {
    return this.lastBeat();
  }

  @Override
  public void addBeats(int beats) {
    //No functionality needed.
  }
}
