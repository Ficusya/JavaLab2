package tests;

import main.ExpressionEvaluator;
import org.junit.Test;
import java.util.EmptyStackException;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionEvaluatorTest {
    @Test
    public void testSimpleExpression() {
        assertEquals(7.0, ExpressionEvaluator.foolProofEvaluation("3 + 4 * (2 - 1)"), 0.001);
    }

    @Test
    public void testInvalidExpression() {
        assertTrue(Double.isNaN(ExpressionEvaluator.foolProofEvaluation("1 + * 2")));
    }

    @Test
    public void testDivisionByZero(){
        assertTrue(Double.isNaN(ExpressionEvaluator.foolProofEvaluation("1 / 0")));
    }

    @Test
    public void testInvalidParentheses() {
        assertTrue(Double.isNaN(ExpressionEvaluator.foolProofEvaluation("(1 + 2 * 3")));
    }
}