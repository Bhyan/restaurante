dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    //cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
    cache.region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory'
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" /* "create-drop" */ // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://localhost/restaurante"
            username = "root"
            password = "root"
        }
    }
    test {
        dataSource {
			dbCreate = "update"
			url = "jdbc:mysql://localhost/restaurantetest"
			username = "root"
			password = "142536"
        }
    }
    production {
        dataSource {
			dbCreate = "update"
			url = "jdbc:mysql://localhost/restaurante"
			username = "root"
			password = "142536"
        }
    }
}
