package behavioural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/**
 * Visitor Design Pattern
 * - Separation between an algorithm and the objects they operate on.
 * - 2 Concepts: visitor and element(visitable)
 * - The element accept visitor type objects.
 * - Visitors performs the operation on the element objects.
 */

interface ReportElement {
    fun <R> accept(visitor: ReportVisitor<R>): R
}

class FixedPriceContract(val costPerYear: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

class TimeAndMaterialContract(val costPerHour: Long, val hours: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

class SupportContract(val costPerMonth: Long) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

interface ReportVisitor<out R> {
    fun visit(contract: FixedPriceContract): R
    fun visit(contract: TimeAndMaterialContract): R
    fun visit(contract: SupportContract): R
}

class MonthlyCostReportVisitor : ReportVisitor<Long> {
    override fun visit(contract: FixedPriceContract): Long = contract.costPerYear.div(12)

    override fun visit(contract: TimeAndMaterialContract): Long = contract.costPerHour.times(contract.hours)

    override fun visit(contract: SupportContract): Long = contract.costPerMonth

}

class YearlyCostReportVisitor : ReportVisitor<Long> {
    override fun visit(contract: FixedPriceContract): Long = contract.costPerYear

    override fun visit(contract: TimeAndMaterialContract): Long = contract.costPerHour.times(contract.hours)

    override fun visit(contract: SupportContract): Long = contract.costPerMonth.times(12)

}

class VisitorTest {

    @Test
    fun testVisitor() {
        val projectAlpha = FixedPriceContract(10_000)
        val projectBeta = SupportContract(500)
        val projectGamma = TimeAndMaterialContract(150, 10)
        val projectKappa = TimeAndMaterialContract(50, 50)

        val project = arrayListOf(projectAlpha, projectBeta, projectGamma, projectKappa)
        val monthlyCostVisitor = MonthlyCostReportVisitor()
        val monthlyCost = project.sumOf { it.accept(monthlyCostVisitor) }

        println("Monthly Cost : $monthlyCost")
        Assertions.assertThat(monthlyCost).isEqualTo(5333)

        val yearlyCostVisitor = YearlyCostReportVisitor()
        val yearlyCost = project.sumOf { it.accept(yearlyCostVisitor) }

        println("Yearly Cost : $yearlyCost")
        Assertions.assertThat(yearlyCost).isEqualTo(20_000)
    }
}