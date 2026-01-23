import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class PasswordTest {

    @Test
    void testPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String senhaTextoPlano = "admin123"; // Troque pela senha que você acha que é
        String hashDoBanco = "$2a$10$ygJjVVy907BquXwKzzvKiubmdJKJBTk.K/NpId5w88i1PAEE3p6D6"; // Hash completo do banco

        boolean matches = passwordEncoder.matches(senhaTextoPlano, hashDoBanco);
        System.out.println("Senha confere? " + matches);
    }

    @Test
    void gerarNovaSenha() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String novaSenha = "paciente123";
        String hash = encoder.encode(novaSenha);

        System.out.println("=================================");
        System.out.println("Nova senha: " + novaSenha);
        System.out.println("Hash gerado: " + hash);
        System.out.println("=================================");
        System.out.println("Execute no banco:");
        System.out.println("UPDATE usuario SET senha = '" + hash + "' WHERE email = 'admin@hospital.com';");
    }
}