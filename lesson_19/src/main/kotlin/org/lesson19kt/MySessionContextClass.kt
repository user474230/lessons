package org.lesson19kt

import org.hibernate.context.internal.ThreadLocalSessionContext
import org.hibernate.engine.spi.SessionFactoryImplementor

class MySessionContextClass(factory: SessionFactoryImplementor) : ThreadLocalSessionContext(factory) {
    override fun isAutoCloseEnabled() = false
    override fun isAutoFlushEnabled() = true
}