package org.lesson19kt

import org.hibernate.cfg.Configuration

fun createTables() {
    val sqlCreateLotTable = """
                    CREATE TABLE IF NOT EXISTS lot (
                    	id int8 NOT NULL,
                    	title text NULL,
                    	price int NULL,
                    	"version" int8 NULL,
                    	user_id int8 NULL,
                    	CONSTRAINT lot_pk PRIMARY KEY (id)
                    );                
                    DELETE FROM lot;
            """
    val sqlCreateUserTable = """
                CREATE TABLE IF NOT EXISTS my_user (
                        	id int8 NOT NULL,
                        	fio text NULL,
                        	CONSTRAINT user_pk PRIMARY KEY (id)
                        );        
                DELETE FROM my_user;    
            """

    Configuration().configure("hibernate2.cfg.xml").buildSessionFactory().use { factory ->
        factory.currentSession.use { session ->
            session.transaction.begin()

            session.createNativeQuery(sqlCreateLotTable).executeUpdate()
            session.createNativeQuery(sqlCreateUserTable).executeUpdate()
            for (i in 0L until 8L) {
                session.save(User(id = i, fio = "user $i"))
            }
            for (i in 0L until 4L) {
                session.save(Lot(id = i, title = "lot $i", price = 0))
            }

            session.transaction.commit()
        }
    }
}
