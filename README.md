# Lab7

Pentru primul exercitiu, am implementat atat speech to text cat si text to speech, din pacate speech to text nu a putut fi 
testat pe un emulator.
Am creat un linear layout ce contine cele doua butoane (listen it si tap to open mic), un textView in care va fi scris textul ascultat si 
un edit text in care introducem textul ce vrem a fi transformat in discurs.

Pentru speech to text, folosim un intent ce preia discursul si il prelucreaza prin speech recognizer (ACTION_RECOGNIZE_SPEECH).
Rezultatele vr fi primite in onActivityResult.

Pentru text to speech folosim clasa cu acceasi denumire, definim un obiect de acest tip(TextToSpeech), il initializam, ii setam limba si 
chemam metoda ce transforma textul in discurs
