iOS ve Android projesini tek merkez kod ile yapmak için kotlin multiplatform projesi açtım. Jetbrains kmp jeneratörünü kullandım (https://kmp.jetbrains.com/). Compose dependencyleri proje ile geldi ama xml kullan denildiği için composable bir elemean kullanmadım.

Projeyi Windows 11 ile, android studio kullanarak geliştirdim. Windows kullandığım için projenin sadece android kısmını yapabildim, iOS kısmını deneyemediğim ve compile da edemediğim için geliştiremedim.

Model ve vievmodel'i shared/src/commonMain içine koydum, buradan iki taraf da (ios, android) kullanabiliyor. iOS'da kotlin native gerektiği için de http erişiminde ktor ve kotlinix.serialization kütüphanelerini kullandım. 

Android uygulaması composeApp içinde. Tamamını kotlin ile yazdım. Scrollerları ViewPager2 ve RecyclerView ile yaptım, view klasöründe MainActivity yanında adaptörleri var. Extensions'da da ImageView'a görsel açıcı ekledim. 

res içinde layout'da layoutlar mevcut. main_layout, horizontal_scroller, vertical_scroller ve product_detail asıl layout. horizontal_scroller_item ve vertical_scroller_item alakalı scrollerlardaki inflate edilen parçalar.

Tema olarak Material3 kullandım, bunlar da value ve values-night'da, ama sadece light mode olarak yaptım. 

İstendiği üzere, projeyi github linki olarak paylaşıyorum. 