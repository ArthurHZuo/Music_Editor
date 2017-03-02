package cs3500.music.view;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorViewModel;
import cs3500.music.model.NoteModel;
import cs3500.music.model.Pitch;

/**
 * Console printout view of the MusicEditorModel
 */
public class ConsoleView implements MusicEditorView {

  private final Appendable output;

  /**
   * Constructs a new ConsoleView with the given output(An Appendable, representing the textual
   * output of the MusicEditorViewModel to be represented)
   *
   * @param output the Appendable to render in the console
   */
  public ConsoleView(Appendable output) {
    this.output = output;
  }

  /**
   * Returns a {@link ArrayList} that lists the range of all of the {@link NoteModel}s pitches in
   * {@code model}
   *
   * @param model the {@link MusicEditorModel} that's range of pitches will be found.
   * @return A {@link ArrayList} that represents the range of all of the the {@link NoteModel}s
   * pitches in {@code model}.
   * @throws IllegalArgumentException if {@code model} is null.
   */
  private static ArrayList<String> getHeader(MusicEditorViewModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    NoteModel lowNote = model.lowestNote();
    NoteModel highNote = model.highestNote();
    ArrayList<String> out = new ArrayList<>();
    boolean inRange = false;

    for (int i = lowNote.getOctave(); i <= highNote.getOctave(); i++) {
      for (Pitch p : Pitch.values()) {
        String tempStr = "";
        if (p == lowNote.getPitch() && !inRange && i == lowNote.getOctave()) {
          inRange = true;
        }
        if (p == highNote.getPitch() && inRange && i == highNote.getOctave()) {
          inRange = false;
          tempStr = highNote.getPitch().toString() + highNote.getOctave();
        }
        if (inRange) {
          tempStr = p.toString() + i;
        }
        if (!tempStr.equals("")) {
          out.add(tempStr);
        }
      }
    }
    return out;
  }

  /**
   * Returns an 2-dimensional Array of Strings that represent the notes in a console view with the
   * 1st dimension being the pitch of the note relative to {@code header} indices and with the 2nd
   * dimension being the beat of the note.
   *
   * @param model  the {@link MusicEditorModel} that's {@link NoteModel}s will converted to console
   *               output.
   * @param header an {@link ArrayList} to describe the range of pitches in {@code model}
   * @return A 2-dimensional Array of Strings that represents notes Positions in the editor where
   * pitch is the first dimension and is based on the indices of {@code header} and the second
   * dimension is the beat the note is on.
   * @throws IllegalArgumentException if either {@code model} or {@code header} are null.
   */
  private static String[][] getBody(MusicEditorViewModel model,
                                    ArrayList<String> header) {
    if (model == null || header == null) {
      throw new IllegalArgumentException("Arguments must be non-null");
    }
    String[][] out = new String[header.size()][model.lastBeat()];
    for (int beat = 0; beat < model.lastBeat(); beat++) {
      for (NoteModel note : model.getNotesPlayingAt(beat)) {
        int pitchI = header.indexOf(note.getPitch().toString() + note.getOctave());
        if (pitchI != -1) {
          out[pitchI][beat] = "|";
        }
      }
      for (NoteModel note : model.getNotesStartingAt(beat)) {
        int pitchI = header.indexOf(note.getPitch().toString() + note.getOctave());
        if (pitchI != -1) {
          out[pitchI][beat] = "♩";
        }
      }
    }
    return out;
  }

  /**
   * Returns a {@link String} representation of the given {@link MusicEditorModel}.
   *
   * @param model the {@link MusicEditorModel} to be made into console output.
   * @throws IllegalArgumentException if {@code model} is null.
   */
  public void view(MusicEditorViewModel model) throws IOException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    ArrayList<String> header = getHeader(model);
    String[][] body = getBody(model, header);
    StringBuilder out = new StringBuilder();
    out.append("   ");
    for (String str : header) {
      if (str.length() == 2) {
        str = " " + str;
      }
      out.append(str);
    }
    out.append("\n");
    for (int beat = 0; beat < body[0].length; beat++) {
      if (beat < 10) {
        out.append("  ").append(beat);
      } else if (beat < 100) {
        out.append(" ").append(beat);
      } else {
        out.append(beat);
      }
      for (int pitch = 0; pitch < body.length; pitch++) {
        String temp = body[pitch][beat];
        if (temp == null) {
          out.append("   ");
        } else {
          out.append(" ").append(temp).append(" ");
        }
      }
      out.append("\n");
    }
    output.append(out.toString());
  }
}
