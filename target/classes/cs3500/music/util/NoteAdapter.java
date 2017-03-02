package cs3500.music.util;

import cs3500.music.model.NoteBuilderImp;
import cs3500.music.model.NoteModel;
import cs3500.music.otherModel.ANote;

import static cs3500.music.otherModel.Note.makeNote;

/**
 *
 */
public class NoteAdapter {

  public static NoteModel adaptANote(ANote n) {
    return new NoteBuilderImp()
        .setStart(n.getStart())
        .setEnd(n.getFinish())
        .setVolume(n.getVolume())
        .setInstrument(n.getInstrument())
        .setPitch(AdaptPitch.adaptTheirPitch(n.getPitch()).getMidiPitch(n.getOctave()))
        .build();
  }

  public static ANote adaptNoteModel(NoteModel n) {
    return makeNote(AdaptPitch.adaptOurPitch(n.getPitch()),
        n.getBeat(),
        n.getBeat() + n.getDuration(),
        n.getOctave(),
        n.getVolume(),
        n.getInstrument());
  }
}
