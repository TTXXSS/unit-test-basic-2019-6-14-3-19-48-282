package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertSame;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.INTERNAL,"firstProject");
        // when
        ExpenseType expense=ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        assertSame(expense, ExpenseType.INTERNAL_PROJECT_EXPENSE);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL,"Project A");
        // when
        ExpenseType expense=ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        assertSame(expense, ExpenseType.EXPENSE_TYPE_A);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL,"Project B");
        // when
        ExpenseType expense=ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        assertSame(expense, ExpenseType.EXPENSE_TYPE_B);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL,"Project TEST");
        // when
        ExpenseType expense=ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        assertSame(expense, ExpenseType.OTHER_EXPENSE);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE,"Project TEST");
        // when
        // then
        Assertions.assertThrows(UnexpectedProjectTypeException.class,()->ExpenseService.getExpenseCodeByProjectTypeAndName(project));
    }
}