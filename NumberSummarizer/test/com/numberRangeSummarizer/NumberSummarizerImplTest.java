package com.test.numberRangeSummarizer;

import com.test.numberRangeSummarizer.NumberRangeSummarizerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberSummarizerImplTest {

    @Autowired
    private NumberRangeSummarizer numberRangeSummarizer;

    @Test
    public void testCollSize() {
        Assert.assertEquals(numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31").size(), 14);
    }

    @Test
    public void testSplit() {
        Collection<Integer> numbers = numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        Assert.assertFalse(numbers.isEmpty());
        Assert.assertEquals(numbers.iterator().hasNext(), true);
    }

    @Test
    public void testSequential() {

        Collection<Integer> numbers = numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
         Assert.assertFalse(Boolean.parseBoolean(Arrays.stream(numbers.toArray()).unordered().toString()));
    }

    @Test
    public void testSummarizedNumbers() {
        String inputNumbers = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String expectedSummary = "1,3,6-8,12-15,21-24,31";

        Collection<Integer> numbers = numberRangeSummarizer.collect(inputNumbers);

        Assert.assertEquals(numberRangeSummarizer.summarizeCollection(numbers), expectedSummary);
    }
}
