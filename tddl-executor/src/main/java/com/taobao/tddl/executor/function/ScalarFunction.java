package com.taobao.tddl.executor.function;

import com.taobao.tddl.optimizer.core.expression.IExtraFunction;
import com.taobao.tddl.optimizer.core.expression.IFunction.FunctionType;
import com.taobao.tddl.optimizer.core.expression.ISelectable.DATA_TYPE;
import com.taobao.tddl.optimizer.exceptions.FunctionException;

/**
 * map是分发过程，reduce是合并过程。<br/>
 * 分发和合并都是在计算节点上进行的（计算节点在客户端内，包含数据节点、合并节点和客户端节点） 其余的与map reduce模式一致。
 * 
 * @author Whisper
 * @author jianghang 2013-11-8 下午3:42:52
 * @since 5.1.0
 */
public abstract class ScalarFunction extends ExtraFunction implements IExtraFunction {

    protected Object result;

    public FunctionType getFunctionType() {
        return FunctionType.Scalar;
    }

    public void serverMap(Object[] args) throws FunctionException {
        this.compute(args);
    }

    public void serverReduce(Object[] args) throws FunctionException {
        this.compute(args);
    }

    public DATA_TYPE getMapReturnType() {
        return this.getReturnType();
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void clear() {
        result = null;
    }

    public String getDbFunction() {
        return function.getColumnName();
    }

    public abstract void compute(Object[] args) throws FunctionException;
}
