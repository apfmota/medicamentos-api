package com.petcc_enfermagem.medicamentos_api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.petcc_enfermagem.medicamentos_api.model.Medicamento;
import com.petcc_enfermagem.medicamentos_api.model.Usuario;
import com.petcc_enfermagem.medicamentos_api.model.Usuario.Role;
import com.petcc_enfermagem.medicamentos_api.service.MedicamentoRepository;
import com.petcc_enfermagem.medicamentos_api.service.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeUsers();
        initializeMedicaments();
    }

    private void initializeUsers() {
        if (usuarioRepository.count() == 0) {
            // Admin user
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setSenha(passwordEncoder.encode("senha"));
            admin.setRole(Role.ADMIN);

            // Regular user
            Usuario user = new Usuario();
            user.setUsername("user");
            user.setSenha(passwordEncoder.encode("senha"));
            user.setRole(Role.USER);

            usuarioRepository.saveAll(Arrays.asList(admin, user));
        }
    }

    private void initializeMedicaments() {
        if (medicamentoRepository.count() == 0) {
            // Dipirona Sódica 1g/2mL
            Medicamento dipirona1 = new Medicamento();
            dipirona1.setId("dipirona-1g-2ml");
            dipirona1.setName("Dipirona Sódica");
            dipirona1.setVariation("1 g / 2 mL");
            dipirona1.setVolumeMl(2.0);
            dipirona1.setAmountMg(1000.0);
            dipirona1.setMgPerKgDefault(15.0);
            dipirona1.setDescription("Analgésico e antipirético utilizado para dor aguda, cólica e controle de febre.");
            dipirona1.setIndications(Arrays.asList("Dor aguda", "Febre", "Cólica"));
            dipirona1.setImage("/images/ampola.svg");

            // Dipirona Sódica 500mg/1mL
            Medicamento dipirona2 = new Medicamento();
            dipirona2.setId("dipirona-500mg-ml");
            dipirona2.setName("Dipirona Sódica");
            dipirona2.setVariation("500 mg / 1 mL");
            dipirona2.setVolumeMl(1.0);
            dipirona2.setAmountMg(500.0);
            dipirona2.setMgPerKgDefault(15.0);
            dipirona2.setDescription("Mesma substância ativa com concentração diferente, indicada quando se deseja menor volume.");
            dipirona2.setIndications(Arrays.asList("Dor moderada", "Febre", "Ajuste fino de dose"));
            dipirona2.setImage("/images/ampola.svg");

            // Morfina 10mg/1mL
            Medicamento morfina = new Medicamento();
            morfina.setId("morfina-10mg-ml");
            morfina.setName("Morfina");
            morfina.setVariation("10 mg / 1 mL");
            morfina.setVolumeMl(1.0);
            morfina.setAmountMg(10.0);
            morfina.setMgPerKgDefault(0.1);
            morfina.setDescription("Opioide para dor intensa em ambiente hospitalar, com monitoramento clínico.");
            morfina.setIndications(Arrays.asList("Dor intensa", "Dor pós-operatória"));
            morfina.setImage("/images/ampola.svg");

            // Furosemida 20mg/2mL
            Medicamento furosemida = new Medicamento();
            furosemida.setId("furosemida-20mg-2ml");
            furosemida.setName("Furosemida");
            furosemida.setVariation("20 mg / 2 mL");
            furosemida.setVolumeMl(2.0);
            furosemida.setAmountMg(20.0);
            furosemida.setMgPerKgDefault(1.0);
            furosemida.setDescription("Diurético de alça utilizado em edema e suporte em situações de sobrecarga hídrica.");
            furosemida.setIndications(Arrays.asList("Edema", "Insuficiência cardíaca", "Hipertensão em urgência"));
            furosemida.setImage("/images/ampola.svg");

            // Ceftriaxona 1g/10mL
            Medicamento ceftriaxona = new Medicamento();
            ceftriaxona.setId("ceftriaxona-1g-10ml");
            ceftriaxona.setName("Ceftriaxona");
            ceftriaxona.setVariation("1 g / 10 mL");
            ceftriaxona.setVolumeMl(10.0);
            ceftriaxona.setAmountMg(1000.0);
            ceftriaxona.setMgPerKgDefault(50.0);
            ceftriaxona.setDescription("Antibiótico de amplo espectro frequentemente utilizado em infecções graves.");
            ceftriaxona.setIndications(Arrays.asList("Pneumonia", "Meningite", "Infecções sistêmicas"));
            ceftriaxona.setImage("/images/ampola.svg");

            // Hidrocortisona 100mg/2mL
            Medicamento hidrocortisona = new Medicamento();
            hidrocortisona.setId("hidrocortisona-100mg-2ml");
            hidrocortisona.setName("Hidrocortisona");
            hidrocortisona.setVariation("100 mg / 2 mL");
            hidrocortisona.setVolumeMl(2.0);
            hidrocortisona.setAmountMg(100.0);
            hidrocortisona.setMgPerKgDefault(2.0);
            hidrocortisona.setDescription("Corticoide para resposta anti-inflamatória e em protocolos de emergência.");
            hidrocortisona.setIndications(Arrays.asList("Reação alérgica", "Broncoespasmo", "Choque"));
            hidrocortisona.setImage("/images/ampola.svg");

            medicamentoRepository.saveAll(Arrays.asList(
                dipirona1, dipirona2, morfina, furosemida, ceftriaxona, hidrocortisona
            ));
        }
    }
}
