package org.lesson19;

import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class MySessionContextClass extends ThreadLocalSessionContext {
    public MySessionContextClass(SessionFactoryImplementor factory) {
        super(factory);
    }

    @Override
    protected boolean isAutoCloseEnabled() {
        return false;
    }

    @Override
    protected boolean isAutoFlushEnabled() {
        return true;
    }
}
