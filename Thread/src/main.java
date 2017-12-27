import java.util.concurrent.Semaphore;

public class main {

	private static Semaphore A = new Semaphore(1);
	private static Semaphore B = new Semaphore(1);

	public static void main(String[] args) throws Exception {
		// Try to print 12A 34B 56C 78D 910E 1112F 1314G 1516H 1718I 1920J 2122K 2324L 2526M 2728N 2930O 3132P 3334Q 3536R 3738S 3940T 4142U 4344V 4546W 4748X 4950Y 5152Z 
		// with Thread A printing number
		// with Thread B printing Char
		B.acquire(); // <--- Hold thread B , let A start first	
		new ThreadA().start();
		new ThreadB().start();
	}


	static class ThreadA extends Thread{
		@Override
		public void run() {

			try {
				for(int i = 1 ; i < 52; ) {

					A.acquire();
					if(i != 1 ) {
						i++;	
					}

					System.out.print(i); 
					i++;
					System.out.print(i);

					B.release();


				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	static class ThreadB extends Thread{

		@Override
		public void run() {
			try {

				for(int i = 1 ; i <= 26; i++) {
					B.acquire();
					System.out.print((char)('A' + i - 1 ) + " ");
					A.release();
				}

			}catch(Exception e) {

			}

		}

	}
}

