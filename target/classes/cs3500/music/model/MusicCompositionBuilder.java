package cs3500.music.model;

import java.util.ArrayList;

import cs3500.music.util.CompositionBuilder;

/**
 * A class for a builder of MusicCompositions. Implements the CompositionBuilder interface.
 */
public class MusicCompositionBuilder implements CompositionBuilder<MusicEditorControllerModel> {
  private final ArrayList<NoteModel> notes;
  private int tempo;

  /**
   * Constructs a MusicCompositionBuilder.
   */
  public MusicCompositionBuilder() {
    this.notes = new ArrayList<>();
    this.tempo = 100;
  }

  @Override
  public MusicEditorControllerModel build() {
    MusicComposition comp = MusicComposition.emptyEditor(tempo);
    for (NoteModel note : notes) {
      comp.addNote(note);
    }
    return comp;
  }

  @Override
  public CompositionBuilder<MusicEditorControllerModel> setTempo(int tempo) {
    this.tempo = tempo;
    return this;
  }

  @Override
  public CompositionBuilder<MusicEditorControllerModel>
  addNote(int start, int end, int instrument, int pitch, int volume) {
    this.notes.add(
        new NoteBuilderImp().setEnd(end).setStart(start).setPitch(pitch)
            .setVolume(volume).setInstrument(instrument).build());
    return this;
  }

}
