package cs3500.music;

import cs3500.music.controller.Controller;
import cs3500.music.model.MusicCompositionBuilder;
import cs3500.music.model.MusicEditorControllerModel;
import cs3500.music.otherView.ModelView;
import cs3500.music.otherView.MusicEditorModelAdapter;
import cs3500.music.otherView.OtherCompositeView;
import cs3500.music.otherView.OtherGuiViewFrame;
import cs3500.music.util.AdaptOtherGuiViewtoOurGuiView;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MusicEditorGuiView;

import javax.sound.midi.MidiUnavailableException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The main class of the program, constructs a new MusicEditorViewModel based off the argument of
 * the file name that is passed in, and then renders that file through either a console, visual or
 * midi view.
 */
public class MusicEditor {

  /**
   * @param args two strings, the first being the name of the text file
   *             to make into a model and the
   *             second being the type of view to render.
   * @throws IOException if a number of arguments other than two are given.
   */
  public static void main(String[] args) throws IOException, MidiUnavailableException {
    if (args.length != 2) {
      throw new IllegalArgumentException("Only 2 arguments allowed");
    }
    MusicEditorControllerModel cm = MusicReader.parseFile(
        new FileReader(args[0]), new MusicCompositionBuilder());
    if (args[1].equals("composite")) {
      new Controller(new OtherCompositeView(new ModelView(new MusicEditorModelAdapter(cm))), cm).run();
    } else {
      getView(args[1], cm).view(cm);
    }
  }

  /**
   * @param str the type of view to produce, either console, visual, or midi
   * @param vm  the MusicEditorViewModel to produce a view for.
   * @return The finished view for the given MusicEditorViewModel and view type.
   */
  static MusicEditorGuiView getView(String str, MusicEditorControllerModel vm)
      throws MidiUnavailableException {
    switch (str) {
      case "visual":
        return new AdaptOtherGuiViewtoOurGuiView(new OtherGuiViewFrame(new ModelView(new MusicEditorModelAdapter(vm))));
      case "composite":
        return new AdaptOtherGuiViewtoOurGuiView(new OtherCompositeView(new ModelView(new MusicEditorModelAdapter(vm))));
    }

    throw new IllegalArgumentException("input must be console, visual, midi, or composite");
  }
}
