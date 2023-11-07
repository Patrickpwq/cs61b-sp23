package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class GuitarHero {

    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static GuitarString[] guitarStrings = new GuitarString[37];
    private static void initialize() {
        for (int i = 0; i < 37; i++) {
            guitarStrings[i] = new GuitarString(440 * Math.pow(2, (double) (i - 24) / 12));
        }
    }

    public static void main(String args[]) {
        initialize();
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index >= 0 && index < 37) {
                    guitarStrings[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString string : guitarStrings) {
                sample += string.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);
            /*
                When a string is plucked, it doesn't just produce a single sound and then stop;
                it vibrates and produces a decaying sound over time.
                Each call to .tic() simulates the passage of time for that ongoing vibration.
                So even if you don't pluck a string during a particular iteration of the loop,
                you still need to call .tic() on it to accurately simulate the natural decay
                of the previously generated sound wave.
             */
            for (GuitarString string : guitarStrings) {
                string.tic();
            }

        }
    }

}
