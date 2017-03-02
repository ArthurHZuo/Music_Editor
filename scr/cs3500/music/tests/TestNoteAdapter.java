import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.model.MusicCompositionBuilder;
import cs3500.music.model.MusicEditorControllerModel;
import cs3500.music.model.NoteModel;
import cs3500.music.util.MusicReader;
import cs3500.music.util.NoteAdapter;

import static org.junit.Assert.assertEquals;

/**
 * Test class for NoteModel to ANote Adpatation
 */
public class TestNoteAdapter {
  @Test
  public void TestNoteConversion() throws FileNotFoundException {
    MusicEditorControllerModel editor = MusicReader.parseFile(
        new FileReader("mystery-1.txt"), new MusicCompositionBuilder());
    for (int i = 0; i < editor.lastBeat(); i++) {
      for (NoteModel n : editor.getNotesStartingAt(i)) {
        NoteModel n2 = NoteAdapter.adaptANote(NoteAdapter.adaptNoteModel(n));
        assertEquals(n, NoteAdapter.adaptANote(NoteAdapter.adaptNoteModel(n)));
      }
    }
  }
}
