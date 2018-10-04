package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uk.ac.cardiffmet.st20072041.Timeline.EventWithFigure;
import uk.ac.cardiffmet.st20072041.Timeline.HistoricalFigure;

class EventWithFigureTest {

	@Test
	void test() {
		EventWithFigure test = new EventWithFigure(EventWithFigure.getFigure(),"go","9999","jk","lm");
	}

}
