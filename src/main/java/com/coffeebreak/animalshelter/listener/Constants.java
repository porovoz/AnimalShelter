package com.coffeebreak.animalshelter.listener;

public final class Constants {
    static final String START_COMMAND = "/start";

    //Информация о приюте для кошек
    static final String INFORMATION_ABOUT_CAT_SHELTER = """
            Добро пожаловать в наш приют для котов! Мы являемся безопасным и заботливым домом для множества котов, которые нуждаются в любви, заботе и внимании. Наша миссия - предоставить временный или постоянный дом для котов, которые были потеряны, брошены или находятся в бедственном положении.
            Мы посвящаем свою жизнь заботе о наших пушистых друзьях, предоставляя им медицинское обслуживание, качественное питание и много любви и ласки. Наша команда состоит из опытных и преданных волонтеров, которые работают круглосуточно, чтобы обеспечить максимальный комфорт и безопасность для котов.
            Мы также с радостью помогаем желающим найти нового члена семьи в свою жизнь. Мы предоставляем возможность усыновить кота, который будет идеально подходить для вашего образа жизни и семейной ситуации.
            Мы надеемся, что вы найдете наш приют для котов уютным и дружелюбным местом, где вы можете узнать больше о наших друзьях-котиках и возможно даже найти своего идеального питомца. Спасибо за ваш интерес к нашей увлекательной работе и мы будем рады ответить на любые ваши вопросы.\s
            \s
            Адрес\s
            г.Москва ул.Кошкина д.1\s
            Телефон:\s
            +7-777-777-77-77\s
            Сайт:\s
            https://www.mos.ru/city/projects/pets/priyut/\s
            """;

    //Информация о приюте для собак
    static final String INFORMATION_ABOUT_DOG_SHELTER = """
            Приют для собак “Друг”
            Наши собаки ждут своих хозяев!
            Если вы хотите завести верного и преданного друга, то наш приют именно для вас! У нас вы найдете своего будущего питомца, который будет любить и защищать вас.
            В нашем приюте живут разные породы собак, от маленьких до больших. Все они нуждаются в любящем хозяине и доме. Если вы готовы стать хозяином для одной из наших собак, то заполните анкету и пройдите собеседование. Мы с радостью поможем вам найти своего питомца!\s
            \s
            Адрес\s
            г.Москва ул.Кошкина д.1\s
            Телефон:\s
            +7-777-777-77-77\s
            Сайт:\s
            https://www.mos.ru/city/projects/pets/priyut/\s
            """;

    static final String INFORMATION_ABOUT_REPORT = """
            Для отчета нужна следующая информация:\s
            - Фото животного  \s
            - Рацион животного\s
            - Общее самочувствие и привыкание к новому месту\s
            - Изменение в поведении: отказ от старых привычек, приобретение новых.\s
            Скопируйте следующий пример и не забудьте прикрепить фото""";

    static final String REPORT_EXAMPLE = """
            Рацион: ваш текст;\s
            Самочувствие: ваш текст;\s
            Поведение: ваш текст;
            """;

    //Часы работы приюта для кошек
    static final String CAT_SHELTER_WORKING_HOURS = """
            Часы работы нашего приюта:\s
            \s
            ПН  8:00-18:00\s
            ВТ  8:00-18:00\s
            СР  8:00-18:00\s
            ЧТ  8:00-18:00\s
            ПТ  8:00-18:00\s
            СБ  8:00-12:00\s
            ВС  Выходной\s
            \s
            Наши сотрудники ждут вас!\s
            """;

    //Часы работы приюта для собак
    static final String DOG_SHELTER_WORKING_HOURS = """
            Часы работы нашего приюта:\s
            \s
            ПН  8:00-16:30\s
            ВТ  8:00-16:30\s
            СР  8:00-16:30\s
            ЧТ  8:00-16:30\s
            ПТ  8:00-16:30\s
            СБ  8:00-12:00\s
            ВС  12-21:00\s
            \s
            Наши сотрудники ждут вас!\s
            """;

    //Оформление пропуска для въезда на территорию приюта для кошек
    static final String CAT_SHELTER_REGISTRATION_OF_PASS = """
            Оформление пропуска на территорию приюта можно оформить на КПП у охранника по приезду или позвонив заранее по тел: +7-777-777-77-77\s
            """;

    //Оформление пропуска для въезда на территорию приюта для собак
    static final String DOG_SHELTER_REGISTRATION_OF_PASS = """
            Оформление пропуска на территорию приюта можно оформить на КПП у охранника по приезду или позвонив заранее по тел: +7-777-777-77-77\s
            """;

    //Правила безопасности в приюте для кошек/собак
    static final String TECHNICAL_SAFETY = """
            Клиенты, которые посещают приют для животных, также должны соблюдать некоторые меры безопасности, чтобы обеспечить безопасность питомцев и себя самих. Ниже приведены некоторые техники безопасности для клиентов:
            1.Соблюдение правил приюта: Клиенты должны следовать правилам приюта и не нарушать их. Это включает в себя не пытаться кормить, гладить или играть с животными без разрешения сотрудников приюта.
            2.Следование инструкциям сотрудников: Клиенты должны следовать инструкциям сотрудников приюта и не пытаться сами заходить в помещения, которые закрыты для посетителей.
            3.Использование средств защиты: В приюте могут быть животные, которые могут линять, царапать или кусать. Клиенты должны использовать средства защиты, такие как перчатки и маски, если необходимо, чтобы избежать травм или заражения.
            4.Наблюдение за своими детьми: Если клиенты приводят детей с собой, они должны наблюдать за ними и не позволять им бегать вокруг животных, гладить их без разрешения или пытаться дразнить их.
            5.Оставление домашних животных дома: Клиенты не должны приводить своих домашних животных в приют, так как это может вызвать конфликты между животными и повысить риск заражения инфекционными заболеваниями.
            6.Соблюдение личной гигиены: Клиенты должны соблюдать личную гигиену, так как это может помочь предотвратить распространение инфекций. Они должны мыть руки с мылом и водой после контакта с животными или поверхностями в приюте.
            7.Безопасное поведение вокруг животных: Клиенты должны быть внимательны и осторожны вокруг животных, чтобы не спугнуть или не напугать их. Они должны держаться на безопасном расстоянии от животных и не пытаться поднимать или переносить их без разрешения сотрудников приюта.
            8.Сообщение о любых проблемах: Если клиенты заметят какие-либо проблемы с животными, такие как поведенческие или медицинские проблемы, они должны немедленно сообщить об этом сотрудникам приюта.
            9.Ответственное отношение к выбору питомца: Если клиенты выбирают питомца для усыновления, они должны быть готовы к ответственности за его уход и заботу, а также к дополнительным расходам на ветеринарное обслуживание и корм.
            10.Соблюдение правил безопасности на дороге: Клиенты должны соблюдать правила дорожного движения, когда подъезжают к приюту и уезжают от него, чтобы избежать возможных аварий или травм.\s
            """;

    //Общая информация если пользователь хочет взять из приюта кота
    static final String INFORMATION_HOW_TO_TAKE_CAT = """
            Если вы рассматриваете возможность взять кота из приюта, вам следует учесть несколько важных факторов:
                      
            1. Обязательства и ответственность: Владение животным, включая кота, требует времени, заботы и финансовых ресурсов. Удостоверьтесь, что вы готовы взять на себя ответственность за заботу о коте на протяжении всей его жизни, которая может продолжаться от 10 до 20 лет.
            2. Подготовка дома: Предварительно подготовьте дом или квартиру для приема нового кота. Обеспечьте безопасное и комфортное пространство, включая место для отдыха, лоток для нужд, место для кормления и игрушки.
            3. Финансовые затраты: Учтите, что содержание кота связано с определенными расходами. Это включает покупку корма, наполнителя для лотка, ветеринарные услуги, прививки, стерилизацию или кастрацию, а также возможные расходы на уход и лечение в случае заболеваний.
            4. Уход и здоровье: Коты требуют регулярного ухода, включая расчесывание шерсти, обрезку когтей и уход за зубами. Также необходимо обеспечить коту регулярные посещения ветеринара для профилактических осмотров и прививок.
            5. Адаптация и терпение: Помните, что кот из приюта может потребовать времени для адаптации к новому дому и семье. Будьте терпеливы и предоставьте ему необходимое время и пространство для привыкания и установления доверительных отношений.
            6. Подбор подходящего кота: При выборе кота обратите внимание на его характер, потребности и совместимость с вашим образом жизни. Обсудите с сотрудниками приюта, какой кот лучше всего подойдет к вашим условиям и предпочтениям.
            7. Сознательное владение: Будьте готовы соблюдать правила и законы, связанные с владением домашнего животного, включая регистрацию и обязательные прививки.
            8. Помощь и поддержка: Постарайтесь узнать больше о поведении и нуждах котов, обратившись к книгам, ресурсам в Интернете или посоветовавшись с ветеринаром. Также может быть полезным присоединиться к сообществу владельцев котов для обмена опытом и получения советов.
            Важно помнить, что взятие кота из приюта - это ответственный шаг, который может принести радость и благополучие в вашу жизнь. Обеспечьте ему любовь, заботу и безопасность, и вы получите преданного и верного друга на многие годы.
            """;

    //Правила знакомства с котом
    static final String RULES_FOR_GETTING_TO_KNOW_A_CAT = """
            1.Знакомство должно проходить в присутствии сотрудников приюта.
            2.Новый хозяин должен быть готов к тому, что кот может быть недружелюбным или агрессивным.
            3.Не стоит пытаться сразу же взять кота на руки - он может испугаться и убежать.
            4.Если кот проявляет интерес к новому хозяину, можно дать ему немного лакомства или погладить его.
            5.Если кот ведет себя агрессивно или боится, не стоит настаивать на знакомстве.
            6.После знакомства необходимо оставить кота на некоторое время, чтобы он мог привыкнуть к новой обстановке.
                        """;

    //Правила знакомства с собакой
    static final String RULES_FOR_GETTING_TO_KNOW_A_DOG = """
            Знакомство нового хозяина и собаки из приюта может быть волнительным и важным шагом для обоих. Вот некоторые правила и рекомендации, которые можно учесть при знакомстве:
            1. Постепенное знакомство: Дайте собаке время на адаптацию и привыкание к новой ситуации. Начните с постепенного знакомства, проводя несколько коротких встреч, чтобы собака могла привыкнуть к вам и вашему запаху.
            2. Устанавливайте контакт на уровне собаки: Когда вы встречаетесь с собакой из приюта, опуститесь на колени или сядьте, чтобы установить контакт на уровне собаки. Это поможет ей чувствовать себя более комфортно и устанавливать доверительные отношения.
            3. Используйте позитивное подкрепление: Во время знакомства с собакой используйте позитивное подкрепление, такое как похвала, ласка или награда вкусной едой. Это поможет собаке связывать вас с положительными и приятными впечатлениями.
            4. Учитесь читать язык тела собаки: Изучите основы языка тела собаки, чтобы понимать ее настроение и сигналы. Узнайте, какие жесты могут указывать на страх, неуверенность или доверие. Это поможет вам адаптировать свое поведение и подход к собаке.
            5. Позвольте собаке исследовать окружающую среду: Дайте собаке возможность исследовать свое новое окружение по своему темпу. Пусть она осмотрит свою новую жилую площадь, но убедитесь, что она находится в безопасной обстановке и не может нанести вред себе или окружающим.
            6. Обеспечьте структуру и рутину: Собаки, особенно из приюта, могут нуждаться в структуре и рутине, чтобы чувствовать себя безопасно и уверенно. Создайте расписание для кормления, прогулок и сна, чтобы собака могла привыкнуть к новому режиму.
            7. Поддерживайте позитивное взаимодействие: Постепенно углубляйте связь с собакой через позитивное взаимодействие. Играйте с ней, обучайте командам, прогуливайтесь вместе и уделяйте время для укрепления взаимной привязанности.
            8. Будьте терпеливы и дайте время: Помните, что каждая собака имеет свой уникальный темп адаптации. Будьте терпеливы и дайте ей время на привыкание к вам и новому дому. Некоторым собакам может потребоваться несколько недель или даже месяцев, чтобы полностью адаптироваться.
            Знакомство с новой собакой из приюта - это процесс, который требует любви и терпения.
                        """;

    //Общая информация если пользователь хочет взять из приюта собаку
    static final String INFORMATION_HOW_TO_TAKE_DOG = """
            Если вы рассматриваете возможность взять собаку из приюта, вам следует учесть несколько важных факторов:
                                                                                                                                                                                                                                                
            1. Обязательства и ответственность: Владение собакой требует времени, заботы и финансовых ресурсов. Удостоверьтесь, что вы готовы взять на себя ответственность за заботу о собаке на протяжении всей ее жизни, которая может продолжаться от 10 до 15 лет или даже больше.
            2. Подготовка дома: Предварительно подготовьте дом или квартиру для приема новой собаки. Обеспечьте безопасное и комфортное пространство, включая место для отдыха, игрушки, кормление и место для ее нужд.
            3. Финансовые затраты: Учтите, что содержание собаки связано с определенными расходами. Это включает покупку корма, прививки, ветеринарные услуги, стерилизацию или кастрацию, а также возможные расходы на уход и лечение в случае заболеваний.
            4. Уход и здоровье: Собаки требуют ежедневного ухода, включая прогулки, тренировки, купание, расчесывание шерсти, уход за зубами и обрезку когтей. Регулярные посещения ветеринара для профилактических осмотров и прививок также необходимы.
            5. Адаптация и терпение: Собака из приюта может потребовать времени для адаптации к новому дому и семье. Будьте терпеливы и предоставьте ей необходимое время и пространство для привыкания и установления доверительных отношений.
            6. Подбор подходящей собаки: При выборе собаки обратите внимание на ее характер, потребности и совместимость с вашим образом жизни. Обсудите с сотрудниками приюта, какая собака лучше всего подойдет к вашим условиям и предпочтениям.
            7. Сознательное владение: Будьте готовы соблюдать правила и законы, связанные с владением домашнего животного, включая регистрацию и обязательные прививки.
            8. Помощь и поддержка: Постарайтесь узнать больше о поведении и нуждах собак, обратившись к книгам, ресурсам в Интернете или посоветовавшись с ветеринаром. Присоединение к сообществу собаководов также может быть полезным для обмена опытом и получения советов.
            Важно помнить, что взятие собаки из приюта - это ответственный шаг, который может принести радость и благополучие в вашу жизнь. Обеспечьте ей любовь, заботу и безопасность, и вы получите преданного и верного друга на многие годы.
            """;

    //Список документов для пользователя, чтобы забрать питомца из приюта (кошек и собак)
    static final String LIST_OF_DOCUMENTS = """
            Чтобы взять питомца из приюта, вам нужно собрать следующие документы:
                                                              
            1.Паспорт или другой документ, удостоверяющий личность.
            2.Заявление на усыновление животного.
            3.Справка о здоровье питомца.
            4.Договор о передаче животного новому владельцу.
            5.Квитанция об оплате за содержание животного в приюте.
            6.Разрешение от всех членов семьи старше 18 лет.
                """;

    //Рекомендации к транспортировке кота
    static final String RECOMMENDATIONS_FOR_CAT_TRANSPORTATION = """
            Транспортировка кота из приюта до дома может быть сложным и стрессовым процессом для животного. Вот несколько рекомендаций, которые помогут сделать процесс более комфортным и безопасным для кота:

            1.Подготовьте все необходимое заранее: убедитесь, что у вас есть переноска, корм, вода, игрушки и другие необходимые вещи.
            2.Выберите удобное время для транспортировки: лучше всего выбрать время, когда кот будет спать или будет находиться в хорошем настроении.
            3.Не кормите кота перед транспортировкой: если кот голоден, он может начать беспокоиться и проявлять агрессию.
            4.Убедитесь, что переноска подходит по размеру: переноска должна быть достаточно большой, чтобы кот мог свободно двигаться и лежать.
            5.Предоставьте коту много внимания и ласки: дайте коту возможность успокоиться и привыкнуть к новым условиям.
            6.Будьте готовы к возможным проблемам: если кот начинает проявлять агрессию или дискомфорт, немедленно остановите транспортировку и обратитесь за помощью к специалистам.
                """;

    //Рекомендации к транспортировке собаки
    static final String RECOMMENDATIONS_FOR_DOG_TRANSPORTATION = """
            Транспортировка собаки из приюта до места жительства может быть довольно сложным и стрессовым испытанием для нее. Вот несколько советов, которые помогут вам сделать этот процесс более комфортным для собаки:
                                                                                                                                                                                     
            1.Подготовьте все необходимые вещи заранее. Убедитесь, что у вас есть ошейник, поводок, миска для воды и еды, а также другие вещи, которые могут понадобиться собаке во время транспортировки.
            2.Выберите удобное время для перевозки. Лучше всего выбрать время, когда собака будет находиться в спокойном состоянии и не будет испытывать стресса.
            3.Не кормите собаку перед перевозкой. Если собака голодна, она может начать нервничать и проявлять агрессию. Вместо этого дайте ей немного еды перед отъездом.
            4.Убедитесь, что клетка или переноска подходят по размеру. Собака должна иметь достаточно места для передвижения и лежания.
            5.Предоставьте собаке много внимания и любви. Дайте ей возможность привыкнуть к новому окружению и успокоиться.
            6.Будьте готовы к возможным проблемам. Если собака проявляет агрессию или стресс, немедленно прекратите перевозку и обратитесь за помощью.
                """;

    //Обустройство жилья для котенка
    static final String HOUSE_ARRANGEMENT_FOR_A_KITTEN = """
            Для котенка необходимо создать комфортные условия проживания. Вот несколько важных аспектов, которые необходимо учесть:
                   
            1.Место для сна и отдыха: котенок должен иметь свое место для сна, где он сможет чувствовать себя защищенным и уединенным. Это может быть коробка, лежанка или просто угол в комнате, где он будет чувствовать себя комфортно.
            2.Игрушки и развлечения: котенок нуждается в играх и развлечениях, чтобы поддерживать свою активность и умственное развитие. Игрушки должны быть разнообразными и интересными, чтобы котенок мог играть с ними в течение длительного времени.
            3.Еда и вода: котенка необходимо кормить и поить регулярно. Еда должна быть качественной и соответствовать его возрасту и потребностям. Вода должна быть доступна в любое время суток.
            4.Гигиена: котенок нуждается в регулярной чистке шерсти и ушей. Также необходимо следить за чистотой лотка и мыть его по мере необходимости.
            5.Безопасность: важно обеспечить безопасность котенка в доме. Установите защитные сетки на окна и двери, чтобы предотвратить побег или попадание внутрь нежелательных гостей. Также убедитесь, что все электрические розетки и провода находятся в недоступном для котенка месте.
            6.Общение: котенок нуждается в общении с людьми и другими животными. Регулярно играйте с ним, разговаривайте и ласкайте его, чтобы он чувствовал себя любимым и нужным.
            """;

    //Обустройство жилья для щенка
    static final String HOUSE_ARRANGEMENT_FOR_A_PUPPY = """
            Обустройство жилья для щенка, взятого из приюта, должно быть максимально комфортным и безопасным. Вот несколько рекомендаций:
                        
            1.Выберите тихое и уютное место в доме или квартире, где щенок сможет чувствовать себя в безопасности. Это может быть спальня, гостиная или другая комната, где нет шума и суеты.
            2.Установите для щенка лежанку или подстилку, на которой он будет спать. Она должна быть удобной и мягкой, чтобы щенок мог расслабиться и отдохнуть.
            3.Обеспечьте щенка мисками для еды и воды. Они должны быть чистыми и удобными для использования.
            4.Установите игрушки для щенка, чтобы он мог играть и развивать свои навыки. Это могут быть мячи, палочки, кольца и другие игрушки, которые помогут ему развивать ловкость и координацию движений.
            5.Следите за чистотой в доме, чтобы избежать инфекций и болезней. Регулярно убирайте мусор и пылесосьте полы.
            6.Обустройте место для прогулок со щенком. Это может быть двор или парк, где он сможет бегать и играть на свежем воздухе.
            7.Не забывайте о регулярных визитах к ветеринару для осмотра и вакцинации щенка. Это поможет сохранить его здоровье и благополучие.
            """;

    //Обустройство жилья для взрослой собаки
    static final String HOUSE_ARRANGEMENT_FOR_AN_ADULT_DOG = """
            Обустройство жилья для взрослой собаки, взятой из приюта, может быть немного сложнее, чем для щенка, но основные принципы остаются теми же. Вот несколько советов:
                        
            1.Определите место, где собака будет спать и отдыхать. Это должно быть тихое и спокойное место, где она будет чувствовать себя защищенной. Лучше всего подойдет спальня или другая комната в доме.
            2.Обеспечьте собаку мисками для еды и воды. Миски должны быть удобными и устойчивыми, чтобы собака не могла их перевернуть или опрокинуть.
            3.Установите игрушки для собаки. Это могут быть мячики, палки, кольца и т.д. Они помогут собаке развивать координацию движений и улучшать настроение.
            4.Следите за гигиеной в доме. Регулярно чистите поверхности и убирайте мусор. Это предотвратит появление бактерий и инфекций.
            5.Обустройте место для прогулки. Это может быть парк или место за городом, где собака сможет свободно бегать и играть.
            6.Не забывайте про регулярные визиты к ветеринару. Это поможет следить за здоровьем собаки и предотвратить возможные проблемы со здоровьем.
            7.Будьте терпеливы и внимательны к потребностям вашей собаки. Она может иметь свои особенности и предпочтения, поэтому важно учитывать их при обустройстве ее жилья.
            """;

    //Обустройство жилья для взрослого кота
    static final String HOUSE_ARRANGEMENT_FOR_AN_ADULT_CAT = """
            Для взрослого кота также необходимо создать комфортные условия проживания, но с учетом его возраста и потребностей. Вот несколько аспектов, которые следует учесть:

            1.Место для сна: взрослый кот может выбрать любое место в доме, которое ему нравится, но желательно, чтобы это было тихое и укромное место. Можно установить специальную когтеточку или лежак для сна.
            2.Игрушки: взрослый кот также нуждается в игрушках, чтобы поддерживать физическую и умственную активность. Можно использовать различные игрушки, такие как мячи, мышки, шарики и т.д.
            3.Еда и вода: взрослый кот нуждается в питании и воде в достаточном количестве. Рекомендуется выбирать качественные корма и следить за тем, чтобы вода была свежей.
            4.Гигиенические процедуры: взрослому коту необходимо регулярно чистить шерсть и уши. Также следует следить за чистотой туалета и мыть его при необходимости.
            5.Общение с человеком: взрослый кот нуждается во внимании и ласке со стороны хозяина. Можно играть с котом, разговаривать с ним и гладить его.
            6.Безопасность: необходимо обеспечить безопасность взрослого кота в доме, установив защитные сетки на окнах и дверях, а также убедившись, что электрические розетки находятся в недоступных для кота местах.
            7.Важно помнить, что каждый кот индивидуален, поэтому необходимо учитывать его предпочтения и потребности при обустройстве жилья.
            """;

    //Обустройство жилья для кота с инвалидностью
    static final String HOUSE_ARRANGEMENT_FOR_DISABLED_CAT = """
            Если вы планируете обустроить жилье для кота с инвалидностью, вам следует учесть несколько важных аспектов, чтобы обеспечить комфорт и безопасность вашего питомца:
                
            1. Безбарьерный доступ: Убедитесь, что ваше жилье обеспечивает легкий и безбарьерный доступ для кота с инвалидностью. Если у него есть проблемы с передвижением, установите низкие или скользкие пороги и исключите ступеньки или другие препятствия на его пути.
            2. Удобное пространство: Создайте удобное пространство для вашего кота, где он может легко перемещаться и отдыхать. Разместите его лежанку или мягкое место в доступной и комфортной зоне.
            3. Игрушки и стимуляция: Предоставьте вашему коту разнообразные игрушки и стимуляцию, чтобы он мог заниматься активными играми и поддерживать физическую активность. Учтите особенности его инвалидности и выбирайте игрушки, которые соответствуют его возможностям.
            4. Облегчение доступа к лотку: Если ваш кот имеет проблемы с передвижением, обеспечьте ему удобный доступ к лотку. Используйте низкий лоток или предоставьте рампу для облегчения подъема и спуска.
            5. Ветеринарное обслуживание: Регулярные визиты к ветеринару особенно важны для кота с инвалидностью. Убедитесь, что у вас есть доступ к качественной ветеринарной помощи и ресурсам, которые могут помочь вам управлять состоянием вашего питомца.
            6. Безопасность: Проанализируйте свое жилье на предмет потенциальных опасностей для кота с инвалидностью. Уберите острые предметы, химические вещества, провода и другие предметы, которые могут причинить ему вред. Установите защитные сетки на окнах, чтобы предотвратить выпадение кота.
            7. Специальные приспособления: В зависимости от инвалидности вашего кота, вы можете рассмотреть возможность использования специальных приспособлений, таких как специальные лестницы или рампы для легкого подъема на мебель или кровать.
            8. Любовь и забота: Важнейшим аспектом заботы о коте с инвалидностью является ваша любовь, терпение и поддержка. Постарайтесь создать положительную и поддерживающую атмосферу, которая поможет вашему питомцу чувствовать себя защищенным и любимым.
            Обратитесь к ветеринару или специалисту по поведению животных, чтобы получить дополнительные советы и рекомендации, специфичные для вашего кота с инвалидностью.
            """;

    //Обустройство жилья для собаки с инвалидностью
    static final String HOUSE_ARRANGEMENT_FOR_DISABLED_DOG = """
            Если вы планируете обустроить жилье для собаки с инвалидностью, вам следует учесть несколько важных аспектов, чтобы обеспечить комфорт и безопасность вашего питомца:             \s
            Вот несколько советов по обустройству жилья для собаки с инвалидностью:
                        
            1.Место для сна: собака должна иметь удобное и безопасное место для сна. Лучше всего использовать специальную лежанку, которая обеспечивает поддержку и защиту для собаки.
            2.Миски для воды и еды: миски для еды и воды должны находиться в легкодоступном месте и быть устойчивыми. Также можно использовать миски с разделителями, чтобы собака могла одновременно есть и пить.
            3.Игрушки: игрушки должны быть безопасными для собаки и не иметь мелких деталей. Лучше выбирать мягкие игрушки с гладкими поверхностями.
            4.Прогулки: если собака может ходить, то место для прогулок должно быть безопасным и удобным для нее. Если же собака не может ходить самостоятельно, то можно использовать специальный поводок или коляску для прогулок.
            5.Уход за шерстью: если у собаки длинная шерсть, то необходимо регулярно расчесывать ее, чтобы предотвратить образование колтунов и спутывание шерсти.
            6.Туалет: для собаки с инвалидностью может быть сложно ходить в туалет на улице, поэтому необходимо обеспечить ей доступ к лотку или пеленке.
            7.Безопасность: необходимо убедиться, что все предметы в доме безопасны для собаки, включая мебель, провода, розетки и т.д.
            8.Общение: если собака не может общаться с людьми, то необходимо найти способы общения с ней, например, использовать жесты или специальные устройства для общения.
            """;

    //Информация о причинах почему животное не может быть выдано новому хозяину
    static final String REFUSAL_REASONS = """
            Есть несколько причин, по которым животное из приюта может быть оставлено там и не выдано новому хозяину:
                     
            1. Несоответствие требованиям и условиям: Приют может иметь определенные требования и критерии для потенциальных владельцев, чтобы убедиться, что животное будет жить в безопасной и подходящей среде. Если вы не соответствуете этим требованиям (например, если у вас нет подходящего жилья, нет достаточного опыта или возможностей обеспечить уход), приют может решить не выдавать вам животное.                    \s
            2. Несовместимость с другими животными или детьми: Некоторые животные могут не совместимы с другими животными в вашем доме или с детьми. Если у приюта есть информация о поведении и социализации животного, которое может указывать на проблемы совместимости, они могут решить не выдавать его вам, чтобы избежать потенциальных проблем или несчастных случаев.                    \s
            3. Здоровье и медицинские проблемы: Если у животного есть серьезные медицинские проблемы, которые требуют специализированного ухода, приют может решить не выдавать его вам, особенно если вы не можете обеспечить нужные условия и стоимость лечения.                    \s
            4. Неправильное поведение или агрессия: Если животное проявляет агрессивное поведение или имеет серьезные проблемы с поведением, приют может считать, что его передача в новый дом может представлять риск для владельца или других людей. В таком случае они могут решить оставить животное в приюте и обратиться к специалистам по поведению животных для дальнейшей работы.                   \s
            5. Ограничения по законодательству: В редких случаях, приют может столкнуться с ограничениями или проблемами, связанными с законодательством или правилами, которые могут предотвратить выдачу животного новому хозяину.                   \s
            Важно понимать, что приюты делают все возможное, чтобы обеспечить благополучие животных и принимают решение о выдаче на основе своих оценок и рекомендаций. Если вам отказали в выдаче животного, рекомендуется обратиться к приюту для получения более подробной информации о причинах и возможных альтернативах.
            """;

    //Советы кинолога
    static final String DOG_HANDLER_TIPS = """
            Кинологи и собаки понимают друг друга с полуслова, по выражению лица/морды и по малейшему жесту. Хотите так же? Это совсем не сложно. Ведущая канала «По жизни с собакой» и профессиональный кинолог Анна Епифанцева делится 6 секретами: правильно хвалите и награждайте питомца, не наказывайте его, изучайте язык тела и будьте последовательными и эмоционально доступными.
            1.Не жалейте награды. Наградой для собаки может быть вкусное лакомство, игры или совместная деятельность. Не жадничайте! Питомцу должно быть приятно находиться рядом с вами.
            2.Никогда не наказывайте собаку. Вы спросите: «Что же, ей теперь всё позволять? Так она на шею мне сядет, лапки свесит и ездить начнет!» Это частая ошибка многих владельцев.
            3.Научитесь понимать язык тела собаки. Живя с человеком, собаки изо всех сил стараются понимать его сигналы. Для них это очень непростая задача, ведь люди очень часто пользуются словами, а речевой аппарат — не самая сильная сторона коммуникации у животных. Они не привыкли болтать друг с другом, как мы, а общаются языком телодвижений.
            4.Правильно хвалите собаку. Многие в качестве поощрения кладут руку на голову собаки между ушей или гладят, натягивая шкуру в сторону хвоста. Многие считают, что так они награждают пса за хорошее поведение.
            5.Будьте последовательны. Очень важно быть последовательным с собакой и сообщать ей о своих намерениях и пожеланиях.
            6.будьте доступными для собаки.Я сейчас сижу за компьютером и пишу эту статью. Моя собака мирно спала в соседней комнате на мягком диване, проснулась и не увидела меня рядом. Тогда она встала и отправилась меня искать. Подошла, уткнулась в меня своим мокрым носом и застыла в ожидании.
            Для нее очень важно убедиться, что я рядом, что всё хорошо. Ей нужно почувствовать связь со мной. И я не гоню ее, а обязательно поглажу, скажу что-нибудь, приглашу поставить на меня лапы. Так она сможет быть ко мне еще ближе. Нам обеим станет спокойнее и приятнее на душе.                  \s
            """;

    //Контакты проверенных кинологов
    static final String DOG_HANDLERS_CONTACT_DATA = """
            Вот список проверенных кинологов, которые смогут вам помочь:
            
            1.Иван Иванович г.Москва тел.+7999888776655
            2.Мария Васильевна г.Москва тел.+7999888776655
            3.Вадим Вадимыч г.Москва тел.+7999888776655
            4.Алеся Кошкина г.Москва тел.+7999888776655
            """;

    // регулярное выражение для проверки правильности ввода данных отчета
    static final String REGEX_MESSAGE_REPORT = """
            ([А-яA-z\\s\\d\\D]+):(\\s)([А-яA-z\\s\\d\\D]+)
            ([А-яA-z\\s\\d\\D]+):(\\s)([А-яA-z\\s\\d\\D]+)
            ([А-яA-z\\s\\d\\D]+):(\\s)([А-яA-z\\s\\d\\D]+)""";

    // chatId добровольца
    static final Long TELEGRAM_CHAT_VOLUNTEER = -8447335152L;

}
