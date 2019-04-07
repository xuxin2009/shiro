package com.sean.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sean.exception.ParamException;
import org.apache.commons.collections.MapUtils;

import javax.validation.*;
import java.util.*;

/**
 * Created by Administrator on 2019/4/6.
 */
public class BeanValidator {

    private  static ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();

    private  static <T> Map<String,String> validate(T t , Class... groups)
    {
        Validator validator = validatorFactory.getValidator();
        Set validateResult = validator.validate(t,groups);
        if(validateResult.isEmpty())
        {
            return Collections.emptyMap();
        }else
        {
            HashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = validateResult.iterator();
            while (iterator.hasNext())
            {
                ConstraintViolation violation= (ConstraintViolation) iterator.next();
                errors.put(violation.getPropertyPath().toString(),violation.getMessage());
            }
            return errors;
        }
    }

    public static Map<String , String>  validateList(Collection<?> collection)
    {
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;
        do {
            if(!iterator.hasNext())
            {
                return Collections.emptyMap();
            }
            Object object = iterator.hasNext();
            errors = validate(object,new Class[0]);//new Class[0]传参表示传一个空数组，和传null的作用类似，但是为什么不用传null呢，因为传null会报空指针异常
        }while (errors.isEmpty());
        return errors;
    }

    public  static Map<String , String> validateObject(Object object,Object... objects)
    {
        if(objects != null && objects.length > 0)
        {
            return validateList(Lists.asList(object,objects));
        }else
        {
            return validate(object,new Class[0]);
        }
    }

    public static void check (Object object) throws ParamException
    {
        Map<String ,String> map = BeanValidator.validateObject(object);
        if(MapUtils.isNotEmpty(map))
        {
            throw new ParamException(map.toString());
        }
    }
}
