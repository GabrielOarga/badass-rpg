package ro.academyplus.model.dto.validators;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatchAnnotation, Object>
{
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatchAnnotation constraintAnnotation)
    {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context)
    {
        try
        {
            BeanWrapperImpl wrapper = new BeanWrapperImpl(value);

            final Object firstObj = wrapper.getPropertyValue(firstFieldName);
            final Object secondObj = wrapper.getPropertyValue(secondFieldName);
            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch (final Exception ignore)
        {
            ignore.printStackTrace();
        }
        return true;
    }
}