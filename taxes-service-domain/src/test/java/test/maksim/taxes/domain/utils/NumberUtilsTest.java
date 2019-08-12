package test.maksim.taxes.domain.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class NumberUtilsTest {

    @Test
    public void scale() {
        assertThat(NumberUtils.scale(1.555004, 3), equalTo(1.555));
        assertThat(NumberUtils.scale(1.3333999, 4), equalTo(1.3334));
    }

    @Test
    public void scale_withDefault2Digits() {
        assertThat(NumberUtils.scale(1.55004), equalTo(1.55));
        assertThat(NumberUtils.scale(1.33999), equalTo(1.34));
    }

    @Test
    public void roundUpTo() {
        assertThat(NumberUtils.roundUpTo(.05, 1.567), equalTo(1.6));
        assertThat(NumberUtils.roundUpTo(.05, 1.467), equalTo(1.5));
    }
}