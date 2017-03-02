import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cs3500.music.model.MusicCompositionBuilder;
import cs3500.music.model.MusicEditorViewModel;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleView;

import static org.junit.Assert.assertEquals;

/**
 * Created by Spencer on 11/12/15. Tests the ConsoleView by pushing the output to a StringBuilder
 * and then making sure it outputs what we want it to output, that is, the formatted string is
 * correct.
 *
 * Tests the first several notes of Mary Had A Little Lamb and Mystery 1, simply because writing out
 * the console output for the whole song would be tedious and a waste of everyones time.
 */
public class TestConsoleView {

  Appendable out = new StringBuilder();
  ConsoleView consoleView = new ConsoleView(out);

  @Test
  public void testMary() throws IOException {
    MusicEditorViewModel m2 =
        MusicReader.parseFile(new FileReader("mary-first6.txt"), new MusicCompositionBuilder());
    consoleView.view(m2);
    assertEquals("    G3G♯3 A3A♯3 B3 C4C♯4 D4D♯4 E4\n" +
        "  0 ♩                          ♩ \n" +
        "  1 |                          | \n" +
        "  2 |                    ♩       \n" +
        "  3 |                    |       \n" +
        "  4 |              ♩             \n" +
        "  5 |              |             \n" +
        "  6 |                    ♩       \n", out.toString());
  }

  @Test
  public void testMystery1() throws IOException {
    MusicEditorViewModel minimalModel =
        MusicReader.parseFile(new FileReader("mystery-1-first6.txt"),
            new MusicCompositionBuilder());
    consoleView.view(minimalModel);
    assertEquals("    C5C♯5 D5D♯5 E5 F5F♯5 G5\n" + "  0             ♩          \n"
        + "  1             |          \n"
        + "  2                        \n" + "  3             ♩          \n"
        + "  4             |          \n" + "  5                        \n"
        + "  6                        \n" + "  7                        \n"
        + "  8                        \n" + "  9             ♩          \n"
        + " 10             |          \n" + " 11                        \n"
        + " 12                        \n" + " 13                        \n"
        + " 14                        \n" + " 15 ♩                      \n"
        + " 16 |                      \n" + " 17                        \n"
        + " 18             ♩          \n" + " 19             |          \n"
        + " 20                        \n" + " 21                        \n"
        + " 22                        \n" + " 23                        \n"
        + " 24                      ♩ \n", out.toString());
  }

  @Test
  public void printOutMystery() throws IOException {
    MusicEditorViewModel model =
        MusicReader.parseFile(new FileReader("mystery-1.txt"),
            new MusicCompositionBuilder());
    FileWriter out = new FileWriter("console-transcript.txt");
    ConsoleView view = new ConsoleView(out);
    view.view(model);
    out.close();
  }
}
