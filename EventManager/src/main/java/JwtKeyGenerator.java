import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        // Génère une clé secrète sécurisée pour l'algorithme HS256
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Convertir la clé en base64 (format à copier dans application.properties)
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

        System.out.println("Voici votre clé secrète sécurisée :");
        System.out.println(base64Key);
    }
}
