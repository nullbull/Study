//package Spring.No3Part;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//
//import javax.sql.DataSource;
//
//public class DataSourceConfig {
//    @Bean(destroyMethod = "shutdown")
//    @Profile("dev")
//    public javax.activation.DataSource embeddedDataSource(){
//        return (javax.activation.DataSource) new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
//                .addScript("classpath:schema.sql")
//                .addScript("classpath:schema.sql")
//                .build();
//    }
//}
