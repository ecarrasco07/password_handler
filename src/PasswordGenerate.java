import java.security.SecureRandom;

//Class to generate passwords
public class PasswordGenerate{

	private static String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*()_+-=[]|,./?><" ;
	private int length = 0;

    PasswordGenerate(int length){
    	this.length = length;
    }

    public char[] generate(){
    	if(length < 0){
			char[] empty = new char[0];
    		return empty;
    	}

    	StringBuilder password = new StringBuilder(length);
		SecureRandom random = new SecureRandom();

    	for(int i = 0; i < length; i++){
    		int index = random.nextInt(CHARS.length());
			password.append(CHARS.charAt(index));
    	}
		String passwordStr = password.toString();
		char[] finalPassword = passwordStr.toCharArray();
		return finalPassword;

    }
}