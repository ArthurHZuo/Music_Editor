import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import cs3500.music.model.MusicCompositionBuilder;
import cs3500.music.model.MusicEditorViewModel;
import cs3500.music.util.DummyMidiDevice;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MidiView;
import cs3500.music.view.MidiViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test Class for MidiViewImpl
 */
public class TestMidiView {

  @Test
  public void testMidiView() throws MidiUnavailableException,
      InvalidMidiDataException, IOException, InterruptedException {
    StringBuilder log1 = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    FileWriter log3 = new FileWriter("midi-transcript.txt");
    MidiView view1 = new MidiViewImpl(new DummyMidiDevice(log1));
    MidiView view2 = new MidiViewImpl(new DummyMidiDevice(log2));
    MidiView view3 = new MidiViewImpl(new DummyMidiDevice(log3));
    MusicEditorViewModel m1 = MusicReader.parseFile(
        new FileReader("mary-first6.txt"), new MusicCompositionBuilder());
    MusicEditorViewModel m2 = new MusicCompositionBuilder()
        .addNote(1, 2, 1, 60, 100)
        .addNote(1, 4, 2, 30, 100)
        .addNote(2, 3, 10, 50, 100)
        .addNote(5, 10, 4, 40, 100).build();
    MusicEditorViewModel m3 = MusicReader.parseFile(
        new FileReader("mary-little-lamb.txt"), new MusicCompositionBuilder());
    for (int i = 0; i < m1.lastBeat(); i++) {
      view1.play(m1, i);
    }
    for (int i = 0; i < m2.lastBeat(); i++) {
      view2.play(m2, i);
    }
    for (int i = 0; i < m3.lastBeat(); i++) {
      view3.play(m3, i);
    }
    assertEquals(log1.toString(),
        "message: -64 0 -1\n" //Instrument change
            + "message: -112 55 70 -1\n" //Start of baseline
            + "message: -128 55 70 1400000\n" //End of baseline note
            + "message: -112 64 72 -1\n" //Start of first melody note
            + "message: -128 64 72 400000\n" //End of first melody note
            + "message: -112 62 72 -1\n"  //Start of next melody note
            + "message: -128 62 72 400000\n" //End of next melody note
            + "message: -112 60 71 -1\n"  //Start of next melody note
            + "message: -128 60 71 400000\n" //End of next melody note
            + "message: -112 62 79 -1\n"  //Start of next melody note
            + "message: -128 62 79 400000\n"); //End of next melody note
    assertEquals(log2.toString(),
        "message: -63 1 -1\n" //Instrument Change
            + "message: -111 30 100 -1\n" //Start note 1
            + "message: -127 30 100 300\n" //End note 1
            + "message: -64 0 -1\n" //Instrument Change
            + "message: -112 60 100 -1\n" //Start note 2
            + "message: -128 60 100 100\n" //End Note 2
            + "message: -55 9 -1\n" //Instrument Change
            + "message: -103 50 100 -1\n" //Start note 3
            + "message: -119 50 100 100\n" //End Note 3
            + "message: -61 3 -1\n" //Instrument Change
            + "message: -109 40 100 -1\n" //Start note 4
            + "message: -125 40 100 500\n"); //End Note 4
    log3.close();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMidiViewExceptions() {
    new MidiViewImpl(null);
  }
}
