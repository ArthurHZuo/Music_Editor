Tom Kowalski Homework 5 Design:

I designed MusicEditorModel to be the interface for the model of a music editor and it allows you to add, remove and edit any NoteModel in the Music Editor as well as get back a List of NoteModels for any NoteModels that either start or are playing on a given beat. This along with a funciton that returns the last beat allow for the client to itterate through the model. There is also a highest and lowest note getters.

My implementation of MusicEditorModel uses an ArrayList of NoteModels sorted by starting beat as its data structure. This allows for more efficient lookups which are more common than additions or removal of NoteModels. I used static factory methods to construct the MusicEditorModel.

I designed NoteModel to be a interface for notes that allow for the getting of the pitch, octave, duration, and start beat of the note. I only allowed for the getting of these fields so that the Note was immutable in order to keep the MusicEditorModel from changing without it knowing. Also I had methods to compare two NoteModels to see if there are equal based on their attributes and to compare them based on pitch.

My implementaion of Note was fairly simple. I did have to override equals(Object other) to use the equals(NoteModel other) method in order to test some methods that returned lists. 

I also had a Pitch enumeral which was used to represent the letter named pitch of notes. I only had sharps and not their equivalent flats because it made other features harder to implement and would not change the accuracy of a model. I overrided toString for convience purposes. 

