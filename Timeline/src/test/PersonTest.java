package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uk.ac.cardiffmet.st20072041.Timeline.HistoricalFigure;

class PersonTest {

	@Test
	void test_if_person_is_created_with_correct_name() {
		HistoricalFigure person =  new HistoricalFigure("James");
		assertEquals("James", person.getName());
	}

}
