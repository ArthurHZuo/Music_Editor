package cs3500.music.util;

import cs3500.music.model.NoteBuilderImp;
import cs3500.music.model.NoteModel;
import cs3500.music.otherModel.ANote;
import cs3500.music.otherModel.Note;
import cs3500.music.otherModel.Pitch;

/**
 * Created by Spencer on 12/7/15.
 * A class to adapt Notes to NoteModels.
 */
public class NoteToNoteModelAdapter {

  /**
   * Given an ANote, adapt it to a NoteModel.
   *
   * @param n the ANote to adapt
   * @return the new NoteModel
   */
  NoteModel adaptTheirNote(ANote n) {
    return new NoteBuilderImp().setStart(n.getStart()).setEnd(n.getFinish())
            .setPitch(AdaptPitch.adaptTheirPitch(n.getPitch()).getMidiPitch(n.getOctave())).build();
  }

  /**
   * Given a NoteModel, return an ANote
   *
   * @param n the NoteModel to adapt
   * @return the adapted ANote
   */
  ANote adaptNoteModel(NoteModel n) {
    return cs3500.music.otherModel.Note.makeNote(AdaptPitch.adaptOurPitch(n.getPitch()), n.getBeat(),
            n.getBeat() + n.getDuration(), n.getOctave(), n.getVolume(), n.getInstrument());
  }
}

