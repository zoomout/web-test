package streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class InputStreamProcessor {

    /**
     * Configurable timeout between processing iterations
     */
    private final int pollTimeout;

    public InputStreamProcessor(int pollTimeout) {
        this.pollTimeout = pollTimeout;
    }

    private static final Predicate<String> NOT_BLANK_LINES = line -> line != null && !"".equals(line);

    /**
     * Processes an input stream until maxLines or maxProcessingTime is reached
     *
     * @param source            input source
     * @param maxLines          amount of lines to process before stopping
     * @param maxProcessingTime maximum time to process the input before returning the collected results
     * @return a list of processed strings
     */
    public List<String> getLinesFromStream(
            final InputStream source,
            final int maxLines,
            final Duration maxProcessingTime) {
        final Instant timeout = Instant.now().plusNanos(maxProcessingTime.toNanos());
        final List<String> processedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(source))) {
            final AtomicInteger counter = new AtomicInteger(0);
            String line;
            while ((counter.get() < maxLines) && (Instant.now().isBefore(timeout))) {
                if (reader.ready()) {
                    while ((counter.get() < maxLines) && reader.ready() && (line = reader.readLine()) != null) {
                        processLine(line, counter, processedLines);
                    }
                } else {
                    try {
                        Thread.sleep(pollTimeout);
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Couldn't process stream");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't close input stream reader");
        }
        return processedLines;
    }

    /**
     * Processing the lines by filtering out the empty ones and counting the rest
     *
     * @param line           line to process
     * @param counter        counter of lines passed through the filter
     * @param processedLines processed lines storage
     */
    private void processLine(final String line, final AtomicInteger counter, final List<String> processedLines) {
        Stream.of(line)
                .filter(NOT_BLANK_LINES)
                .peek(notUsed -> counter.incrementAndGet())
                .forEach(processedLines::add);
    }

}
