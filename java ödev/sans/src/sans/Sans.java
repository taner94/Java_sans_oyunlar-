package sans;


import java.util.Scanner;
import java.util.Random;
public class Sans {
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		/*kullanılacak değişkenler oluşturulmuştur. kolon 2 boyutlu int türünde bir dizidir bu değişken kullanıcının şanslı numaraları
		saklayacak dizidir. cekilis dizisi ise yapılacak olan çekilişte çekilecek olan sayıları saklamaktadır. KolonSayisi ise kullanıcın 
		kaç kolon oynayacağı sorusuna karşılık alınan sayıyı saklayacaktır. ve secim değişkeni ise kullanıcının hangi oyunu oynayacağını saklayacaktır.*/
		int kolon[][],cekilis[],KolonSayisi,secim; 
		String OynamaSekli;
		
		/*Menü Yazılır ve kullanıcıya hangi oyunu oynayacağı sorulur.*/
		System.out.println("1-On Numara\n" +
						   "2-Şans Topu\n" +
						   "3-Süper Loto\n" +
						   "4-Sayısal Loto");
		
		System.out.println("Lütfen menüden oynamak istediğiniz oyunu seçiniz.");
		secim = input.nextInt();
		
		/*Eğer kullanıcı menüden seçim yapmaz ise bu kod çalışır ve menüden geçerli bir seçim yapasıya kadar devam eder.*/
		while(secim < 1 || secim > 4)
		{
			System.out.println("Menüden geçerli bir seçim yapınız:\n");
		
			System.out.println("1-On Numara\n" +
					   "2-Şans Topu\n" +
					   "3-Süper Loto\n" +
					   "4-Sayısal Loto");
	
			System.out.println("Lütfen menüden oynamak istediğiniz oyunu seçiniz.");
		
			secim = input.nextInt();
		}
		
		/*Kullanıcıya kaç kolon oynayacağı sorulur*/
		System.out.println("Kaç kolon oynamak istersiniz.");
		KolonSayisi = input.nextInt();
		
		/*Eğer kolon sayısı 1 den küçükse kullanıcıya geçerli bir kolon sayısı girmesi söylenir.*/
		while(KolonSayisi<1)
		{
			System.out.println("Kolon Sayısı için geçerli bir sayı giriniz:");
			KolonSayisi = input.nextInt();
		}
		/*Kolon dizisi oluşturulur*/
		if(secim == 1)/*on numara için*/
		{
			kolon = new int[KolonSayisi][10];
			cekilis = new int[22];//On numarada 22 sayı çekilir.
		}
		else/*diğer oyunlar için şans topu 5+1 dir ondan dolayı onun içinde burası kullanılmıştır.*/
		{
			kolon = new int[KolonSayisi][6];
			cekilis = new int[6];// diğer oyunların hepsinde 6 tane sayı çekilir.
		}
		
		/*Kullancıya Nasıl oynamak istediği sorulur*/
		System.out.println("Kendiniz mi oynamak istersiniz?(E/H)");
		OynamaSekli = input.next();
		
		OynamaSekli = OynamaSekli.toUpperCase();//eğer kullanıcı "e" veya "h" girerse diye büyük harfe dönüştürülür.
		
		/*eğer kullanıcının girdiği değer geçerli değilse geçerli bir değer giresiye kadar while döngüsü çalışır*/
		while(!OynamaSekli.equals("E")  && !OynamaSekli.equals("H"))
		{
			System.out.println("Lütfen geçerli bir giriş yapınız! Kendiniz mi oynamak istersiniz?(E/H)");
			OynamaSekli = input.next();
		}
		
		if(OynamaSekli.equals("E"))/*eğer kullanıcı E girerse KendinOyna metodu çağrılır.*/
		{
			kolon = KendinOyna(secim,KolonSayisi);
		}
		else/*Eğer kullanıcı H girerse MakinayaOynat metodu çağrılır.*/
		{
			kolon = MakinayaOynat(secim,KolonSayisi);
		}
		
		/*CekilisYap metodu çağrılr ve dönen değer cekilis değişkenine atanır*/
		cekilis = CekilisYap(secim);
		
		
		/*Kullanıcın enter girmesi beklenir.*/
		System.out.println("Sonucları görmek için ENTER'a basınız!");
		input.nextLine();
		input.nextLine();
		
		/*Ve çekiliş kontrol edilmesi CekilisKontrol metodu çağrılır.*/ 
		CekilisKontrol(kolon,cekilis,secim);
		
	}
	
	/*kullanıcının oynamak istediği oyunu bilmek için secim parametresi gerekmektedir. Ve kolon sayısı alınmıştır.*/
	public static int[][] MakinayaOynat(int secim, int kolon)
	{
		Random randomNum = new Random(); //rastgele sayılar üretilmesi için randomNum nesnesi oluşturulmuştur.
		
		/*iki boyutlu kolonlar dizisi ve rasgetele üretilen sayıyı tutmak için sayi dğişkeni oluşturulmuştur.*/
		int kolonlar [][],sayi; 
		/*rastgele üretilen sayıyının aynısının bir kolonda olmamamsı için KontrolKolon metodunun döndürdüğü değeri tutmak için kullanılacaktır.*/
		boolean kontrol;
		
		/*Kullanıcının secimine ve oynamak istediği kolon sayısına göre kolonlar değişkenin boyutları belirlenmiştir.*/
		if(secim == 1)
		{
			kolonlar = new int[kolon][10];
		}
		else
		{
			kolonlar = new int[kolon][6];
		}
		
		/*kolonlar dizisinin her bir elemanı sıfıra eşitlenir. Sıfıra eşitlenmesindeki mantık şudur. KontrolKolon metodunu çağırdığımız zaman.
		Kolonun tamanını tarayacağı için bir elemanın içinde değer yoksa program çalşırken hata verecektir. Onu engellemek için bu işlem yapılmaktadır.*/
		for(int i = 0; i<kolon; i++)		
			for(int j = 0; j<kolonlar[0].length; j++)
				kolonlar [i][j] = 0;
		
		/*Kolonlar oluşturulmaktadır.*/
		for(int i = 0; i<kolon; i++)
		{
			//her kolon doldurulmadan önce j = 0 yapılmıştırki o kolonun elemanları oluşturulabilsin.
			int j = 0;
			/*İki for yerine bir for ve bir while oluşturulmuştur.*/
			while(j<kolonlar[0].length)
			{
				/*Secime göre üretilecek sayı belirlenmiştir*/
				if(secim == 1 )
				{
					sayi = randomNum.nextInt(80)+1;
				}
				else if(secim == 2 && j == 5)/*Şans topu oyunu için ayrıca bir else if eklenmiştir +1 sayıyı üretebilemk için.*/
				{
					sayi = randomNum.nextInt(14)+1;
				}
				else if(secim == 2)
				{
					sayi = randomNum.nextInt(34)+1;
				}
				else if(secim == 3)
				{
					sayi = randomNum.nextInt(54)+1;
				}
				else
				{
					sayi = randomNum.nextInt(49)+1;
				}
				
				/* her sayı üretildikten sonra KontrolKolon medtodu çağrılır ve üretilen sayı önceden üretildiyse true döndürür üretilmediyse false.*/
				
				if(!(secim == 2 && j == 5))/*Şans topu oyunu için ayrıca bir else if eklenmiştir +1 sayıyı üretebilemk için.*/
				{
					kontrol = KontrolKolon(kolonlar[i],sayi);
				}
				else
				{
					kontrol = false;
				}
				
				/*Eğer sayi daha önceden üretilmediyse kolonlar değişkeninin atanması gereken elemanına sayi atanor ve j bir arttırılır.
				eğer üretilen sayı daha önce varsa bu if'in içine girilmez ve sayi tekrardan üretilir.*/
				if(!kontrol)
				{
					kolonlar[i][j] = sayi;
					j++;
				}
			}	
		}
		
		/*Ve kolonlar tamamen oluşturulduktan sonra, metodun çağrıldığı yere iletilirler.*/
		return kolonlar;
	}
	
	/*kullanıcının oynamak istediği oyunu bilmek için secim parametresi gerekmektedir. Ve kolon sayısı alınmıştır.*/
	public static int[][] KendinOyna(int secim, int kolon)
	{
		/*Kullanıcı sayiları gireceği için Scanner sınıfından bir nesne oluşturulmuştur*/
		Scanner input = new Scanner(System.in); 
	
		/*Kullanıcın giridiği sayıyının aynısının bir kolonda olmamamsı için KontrolKolon metodunun döndürdüğü değeri tutmak için kullanılacaktır.*/
		boolean kontrol = true;
		/*iki boyutlu kolonlar dizisi ve kullanıcının girdiği sayıyı tutmak için sayi dğişkeni oluşturulmuştur.*/
		int kolonlar [][],sayi;
		
		
		/*Kullanıcının secimine ve oynamak istediği kolon sayısına göre kolonlar değişkenin boyutları belirlenmiştir.*/
		if(secim == 1)
		{
			kolonlar = new int[kolon][10];
		}
		else
		{
			kolonlar = new int[kolon][6];
		}
		
		
		/*kolonlar dizisinin her bir elemanı sıfıra eşitlenir. Sıfıra eşitlenmesindeki mantık şudur. KontrolKolon metodunu çağırdığımız zaman.
		Kolonun tamanını tarayacağı için bir elemanın içinde değer yoksa program çalşırken hata verecektir. Onu engellemek için bu işlem yapılmaktadır.*/
		for(int i = 0; i<kolon;i++)		
			for(int j = 0; j<kolonlar[0].length; j++)
				kolonlar [i][j] = 0;
				
		/*Kullanıcı kolonlarını doldurmaya başlıyor.*/
		for(int i = 0; i<kolon;i++)
		{
			System.out.println(i+1 +". kolonu giriniz.\n");
			
			for(int j = 0; j<kolonlar[0].length; j++)
			{
				/*Kullanıcı şans topu oynuyorsa ve 5 sayıyı girdiyse +1 i girmesi için yazılacak metnin oluşturulması için yapılmıştır.*/
				if(secim == 2 && j == 5) 
				{
					System.out.println(i+1 + ". kolonun + 1'ini  giriniz ");
					sayi =  input.nextInt();
				}
				else
				{
					System.out.println(i+1 + ". kolonun " + (j+1) + ". sayısını giriniz ");
					sayi =  input.nextInt();
				}
				
				/*burdaki if else - else if kontrol yapısında kullanıcının girdiği sayının oynadığı oyuna göre belli aralıkta olmasını kontrol ediyor  */
				if(secim == 1)//on numara oynuyor ise kullanıcıın girdiği sayı 1-80 sayı kümesinden olmalı
				{
					while(sayi<1 || sayi>80)
					{
						System.out.println("\nLütfen girdiğiniz sayı 1-80 sayı kümesinden olmalıdır");
						System.out.println(i+1 + ". kolonun " + j+1 + ". sayısını giriniz ");
						sayi =  input.nextInt();
					}
					
					kontrol = KontrolKolon(kolonlar[i],sayi);
				}
				else if(secim == 2 && j == 5) //şans topu oynuyor ve +1 i giriyorsa sayı 1-14 sayı kümsesinden olmalı.
				{
					while(sayi<1 || sayi>14)
					{
						System.out.println("\nLütfen girdiğiniz sayı 1-14 sayı kümesinden olmalıdır");
						System.out.println(i+1 + ". kolonun + 1'ini  giriniz ");
						sayi =  input.nextInt();
					}
				}
				else if(secim == 2) // şans topu oynuyorsa sayi 1-34 sayı kümesinden olmalı.
				{
					while(sayi<1 || sayi>34)
					{
						System.out.println("\nLütfen girdiğiniz sayı 1-34 sayı kümesinden olmalıdır");
						System.out.println(i+1 + ". kolonun " + j+1 + ". sayısını giriniz ");
						sayi =  input.nextInt();
					}
					
					kontrol = KontrolKolon(kolonlar[i],sayi);
				}
				else if(secim == 3) // süper loto oynuyorsa sayi 1-54 sayi kümesinden olmalı
				{
					while(sayi<1 || sayi>54)
					{
						System.out.println("\nLütfen girdiğiniz sayı 1-54 sayı kümesinden olmalıdır");
						System.out.println(i+1 + ". kolonun " + j+1 + ". sayısını giriniz ");
						sayi =  input.nextInt();
					}
					
					kontrol = KontrolKolon(kolonlar[i],sayi);
				}
				else //sayisal loto oynuyorsa sayi 1-49 sayi kümesinden olmalıdır.
				{
					while(sayi<1 || sayi>49)
					{
						System.out.println("\nLütfen girdiğiniz sayı 1-49 sayı kümesinden olmalıdır");
						System.out.println(i+1 + ". kolonun " + j+1 + ". sayısını giriniz ");
						sayi =  input.nextInt();
					}
					
					kontrol = KontrolKolon(kolonlar[i],sayi); // kullanıcının girdiği sayı tekrar girmesin diye kontrol yapılmaktadır.
				}
				
				
				if(kontrol)// eğer kullanıcı aynı sayıyı girmişse bu ifin içine girer ve kullanıcı uyarılır. tekrardan aynı sayıyı girmesi içinde j bir azaltılır.
				{
					System.out.println("Bir kolonda aynı sayıdan iki tane olamaz.");
					j--;
				}
				else// eğer kullanıcı var olan bir sayıyı girmediyse sayı gerekli yere atanır.
				{
					kolonlar[i][j] = sayi;
				}
			}
		}
		
		return kolonlar;
	}
	
	/*Kullanıcının girdiği veya rastgele üretilen sayının tekrardan kolona yazılmaması için bu metod kullanılmıştır.*/	
	public static boolean KontrolKolon(int kolon[], int kontrolSayi)
	{
		boolean kontrol = false;
		
		for(int i = 0; i< kolon.length;i++)
		{
			if(kolon[i] == kontrolSayi)
				kontrol = true;
		}
		
		return kontrol;		
	}
	
	/*çekilişin yapıldığı metod*/
	public static int[] CekilisYap(int secim)
	{
		Random randomNum = new Random();
		int kolon[],sayi;
		boolean kontrol;
		
		if(secim == 1)//On numara çekilişinde 22 sayı çekiliyor. ve ona göre dizi oluşturuluyor
		{
			kolon = new int[22];
		}
		else
		{
			kolon = new int[6];
		}
		
		/*Kontrolde sıkıtı yaşamamk için hepsi sıfıra atanıyor*/
		for(int i = 0; i<kolon.length; i++) 
		{
			kolon[i] = 0;
		}
		
		int i = 0;
	
		while(i < kolon.length) //Kullanıcın oynadığı oyuna göre çekiliş yapılıyor
		{
			if(secim == 1 )
			{
				sayi = randomNum.nextInt(80)+1;
			}
			else if(secim == 2 && i == 5)
			{
				sayi = randomNum.nextInt(14)+1;
			}
			else if(secim == 2)
			{
				sayi = randomNum.nextInt(34)+1;
			}
			else if(secim == 3)
			{
				sayi = randomNum.nextInt(54)+1;
			}
			else
			{
				sayi = randomNum.nextInt(49)+1;
			}
			
			if(!(secim == 2 && i == 5))/*Şans topu oyunu için ayrıca bir else if eklenmiştir +1 sayıyı üretebilemk için.*/
			{
				kontrol = KontrolKolon(kolon,sayi);
			}
			else
			{
				kontrol = false;
			}
			
			if(!kontrol)
			{
				kolon[i] = sayi;
				i++;
			}
		}
	
		return kolon;
	}

	/*Çekiliş sonucunda hangi kolondan kaç tuttuğunun hesaplanıp gösterilmesi. */ 
	public static void CekilisKontrol(int kolonlar[][], int cekilis [], int secim)
	{
		int tutanSayisi,artiBir;
		System.out.println("Şansı Numaralar:");
	
		for(int i = 0; i<cekilis.length; i++)
		{
			/*Çekiliş sonucunda çekilen sayıları düzgün göstermek için yazılmıştır*/
			if(secim == 2 && i == 5)
			{
				System.out.print(" + " + cekilis[i]);
			}
			else
			{
				if(i != 0)
				{
					System.out.print("-" + cekilis[i]);
				}
				else{
					
					System.out.print( cekilis[i]);
					
				}
				
			}
		}
		
		System.out.println("\n");
		
		/*Gerekli karşılaştırmaları yapıp sonucları gösterir*/
		for(int i = 0; i<kolonlar.length; i++)
		{
			tutanSayisi = 0;
			artiBir = 0;
			System.out.print(i+1 + ". Kolon = ");
			
			for(int j = 0; j<kolonlar[0].length; j++)
			{
				System.out.print(kolonlar[i][j] + "-");
				
				if(secim == 2 && j == 5)
				{
					if(kolonlar[i][j] == cekilis[j])
						artiBir++;
				}
				else
				{
					for(int k = 0; k<cekilis.length; k++)
					{
						if(kolonlar[i][j] == cekilis[k])
							tutanSayisi++;
					}
				}
			}
			System.out.println();
			if(secim == 2)
			{
				System.out.println(i+1 + ". kolonunuzdan " + tutanSayisi + "+" + artiBir + "Tutmuştur.");
			}
			else
			{
				System.out.println(i+1 + ". kolonunuzdan " + tutanSayisi + " tutmuştur.");
			}
			System.out.println();
		}
	}
}
