package controle.projetos.migracao;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MigracaoMain {

	private static Logger log = Logger.getLogger(MigracaoMain.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		log.info("Iniciada aplicação de migração");
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		MigracaoService migracaoService = context.getBean(MigracaoService.class);
		migracaoService.realizarMigracao();
	}
	
}
