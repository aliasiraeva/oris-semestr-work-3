<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Главная страница</title>
    <style>
        * { box-sizing: border-box; }
        html, body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: #f9f9f9;
        }

        .topbar {
            display: flex;
            justify-content: flex-end;
            background: #fff;
            padding: 10px 20px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }
        .topbar a {
            margin-left: 20px;
            text-decoration: none;
            color: #6C5CE7;
            font-weight: bold;
            font-size: 14px;
        }

        .ad-carousel-wrapper {
            max-width: 1100px;
            margin: 20px auto;
            overflow: visible;
        }
        .ad-carousel {
            position: relative;
            width: 100%;
            height: 64px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .ad-window {
            width: 100%;
            height: 100%;
            overflow: hidden;       /* прячем соседние слайды */
            border-radius: 8px;
        }
        .ad-slides {
            display: flex;
            transition: transform .3s ease;
            height: 100%;
        }
        .ad-slide {
            flex: 0 0 100%;         /* ровно 100% ширины окна */
            display: flex;
            align-items: center;
            justify-content: space-between; /* кнопка справа */
            padding: 0 16px;        /* вот здесь задаём отступ */
        }
        .ad-icon {
            width: 40px;
            height: 40px;
            margin-right: 12px;
            flex-shrink: 0;
        }
        .ad-text {
            flex: 1;
            font-size: 14px;
            color: #333;
            margin: 0;
            margin-right: 12px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .ad-button {
            background: #6C5CE7;
            color: #fff;
            border: none;
            padding: 6px 12px;
            font-size: 14px;
            border-radius: 4px;
            cursor: pointer;
        }
        .ad-arrow {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            width: 24px; height: 24px;
            background: none;
            border: none;
            cursor: pointer;
            z-index: 1;
        }
        .ad-prev { left: -32px; }
        .ad-next { right: -32px; }
        .ad-arrow:before {
            content: '';
            display: block;
            width: 12px; height: 12px;
            border-top: 2px solid #5D4037;
            border-right: 2px solid #5D4037;
            margin: auto;
            transform: rotate(-135deg);
        }
        .ad-next:before { transform: rotate(45deg); }

        .filters {
            display: flex;
            gap: 20px;
            padding: 20px;
        }
        .filters select {
            flex: 1;
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background: #fff;
        }
        .grid {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            padding: 0 20px 40px;
        }
        .card {
            background: #fff;
            flex: 0 0 calc((100% - 60px) / 4);
            max-width: calc((100% - 60px) / 4);
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
            overflow: hidden;
            cursor: pointer;
            display: flex;
            flex-direction: column;
        }
        .card img {
            width: 100%;
            height: 400px;
            object-fit: cover;
        }
        .card .info {
            padding: 10px;
            flex-grow: 1;
        }
        .card .title {
            margin: 0 0 6px;
            font-size: 16px;
            color: #222;
        }
        .card .loc {
            margin: 0;
            font-size: 12px;
            color: #666;
        }
    </style>
</head>
<body>

<div class="topbar">
    <a href="profile.html">Профиль</a>
</div>

<div class="ad-carousel-wrapper">
    <div class="ad-carousel">
        <button class="ad-arrow ad-prev" aria-label="Предыдущий"></button>
        <div class="ad-window">
            <div class="ad-slides" id="adSlides">
                <div class="ad-slide">
                    <img src="images/ad_icon.png" class="ad-icon" alt="Груминг">
                    <div class="ad-text">Профессиональный груминг — скидка 10% при первом визите</div>
                    <button class="ad-button" onclick="window.open('https://grooming.example.com','_blank')">Перейти</button>
                </div>
                <div class="ad-slide">
                    <img src="images/ad_icon_store.png" class="ad-icon" alt="Магазин еды">
                    <div class="ad-text">Лучшие корма и лакомства — бесплатная доставка от 2000₽</div>
                    <button class="ad-button" onclick="window.open('https://foodstore.example.com','_blank')">Перейти</button>
                </div>
                <div class="ad-slide">
                    <img src="images/ad_icon_hotel.png" class="ad-icon" alt="Отель для животных">
                    <div class="ad-text">Гостиница для питомцев — уход и забота 24/7</div>
                    <button class="ad-button" onclick="window.open('https://pet-hotel.example.com','_blank')">Перейти</button>
                </div>
            </div>
        </div>
        <button class="ad-arrow ad-next" aria-label="Следующий"></button>
    </div>
</div>

<div class="filters">
    <select id="categoryFilter">
        <option value="all">Все категории</option>
        <option value="LOSS">Пропажа</option>
        <option value="FIND">Найдено</option>
        <option value="SALE">Продажа</option>
    </select>
    <select id="tagFilter">
        <option value="all">Все теги</option>
        <option value="собака">Собака</option>
        <option value="кошка">Кошка</option>
        <option value="ретривер">Ретривер</option>
        <option value="пудель">Пудель</option>
    </select>
</div>

<div class="grid" id="adsGrid"></div>

<script>
    (function(){
        const slides = document.getElementById('adSlides');
        let idx = 0, total = 3;
        document.querySelector('.ad-prev').onclick = () => {
            idx = (idx - 1 + total) % total;
            slides.style.transform = `translateX(-${idx*100}%)`;
        };
        document.querySelector('.ad-next').onclick = () => {
            idx = (idx + 1) % total;
            slides.style.transform = `translateX(-${idx*100}%)`;
        };
    })();

    const announcements = [
        {id:1,title:'Пропал той-пудель',category:'LOSS',tags:['собака','пудель'],location:'Москва, Тверская, 1',imageUrl:'images/img.png'},
        {id:2,title:'Найден кот',category:'FIND',tags:['кошка'],location:'Санкт-Петербург, Невский, 10',imageUrl:'images/img_1.png'},
        {id:3,title:'Продаётся джунгарский хомяк',category:'SALE',tags:['хомяк','джунгарик'],location:'Екатеринбург, Мира, 5',imageUrl:'images/img_3.png'},
        {id:4,title:'Продается волнистик',category:'SALE',tags:['попугай'],location:'Новосибирск, Ленина, 45',imageUrl:'images/img_2.png'}
    ];
    const grid = document.getElementById('adsGrid');

    function render(list) {
        grid.innerHTML = '';
        list.forEach(a => {
            const c = document.createElement('div');
            c.className = 'card';
            c.onclick = () => window.location.href = `announcement.html?id=${a.id}`;
            c.innerHTML = `
          <img src="${a.imageUrl}" alt="${a.title}">
          <div class="info">
            <p class="title">${a.title}</p>
            <p class="loc">${a.location}</p>
          </div>`;
            grid.append(c);
        });
    }

    document.getElementById('categoryFilter').onchange =
        document.getElementById('tagFilter').onchange = () => {
            const cat = document.getElementById('categoryFilter').value;
            const tag = document.getElementById('tagFilter').value;
            let f = announcements;
            if (cat!=='all') f = f.filter(a=>a.category===cat);
            if (tag!=='all') f = f.filter(a=>a.tags.includes(tag));
            render(f);
        };

    render(announcements);
</script>

</body>
</html>
