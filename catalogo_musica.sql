CREATE DATABASE catalogo_musica.db;

-- Tabla artistas
CREATE TABLE artistas (
    id_artista INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    pais TEXT,
    genero TEXT,
    descripcion TEXT
);

-- Tabla álbumes
CREATE TABLE albumes (
    id_album INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    id_artista INTEGER,
    año INTEGER,
    genero TEXT,
    portada TEXT,
    FOREIGN KEY (id_artista) REFERENCES artistas(id_artista)
);

-- Tabla canciones
CREATE TABLE canciones (
    id_cancion INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    id_album INTEGER,
    id_artista INTEGER,
    duracion TEXT,
    genero TEXT,
    FOREIGN KEY (id_album) REFERENCES albumes(id_album),
    FOREIGN KEY (id_artista) REFERENCES artistas(id_artista)
);

-- =======================
-- INSERCIONES DE ARTISTAS
-- =======================
INSERT INTO artistas (nombre, pais, genero, descripcion) VALUES 
('Bad Bunny', 'Puerto Rico', 'Reggaeton', 'Benito Antonio Martínez Ocasio, revolucionó el reggaeton con su estilo único y colaboraciones internacionales.'),
('Daddy Yankee', 'Puerto Rico', 'Reggaeton', 'Ramón Luis Ayala Rodríguez, pionero del reggaeton. Conocido por hits globales como "Gasolina".'),
('J Balvin', 'Colombia', 'Reggaeton', 'José Álvaro Osorio Balvín, líder del movimiento "Urban Latino". Conocido por su estilo colorido.'),
('Marc Anthony', 'Puerto Rico', 'Salsa', 'Marco Antonio Muñiz, uno de los artistas tropicales más vendidos. Rey de la salsa romántica.'),
('Celia Cruz', 'Cuba', 'Salsa', 'La Reina de la Salsa. Icono de la música latina con más de 50 años de carrera.'),
('Shakira', 'Colombia', 'Pop Latino', 'Shakira Isabel Mebarak Ripoll, conocida por sus fusiones de pop rock con sonidos latinos.'),
('Carlos Vives', 'Colombia', 'Vallenato', 'Renovador del vallenato tradicional fusionándolo con pop, rock y sonidos caribeños.'),
('Juanes', 'Colombia', 'Rock Latino', 'Juan Esteban Aristizábal, fusiona rock con ritmos latinos. Activista social.'),
('Maluma', 'Colombia', 'Reggaeton', 'Juan Luis Londoño Arias, representante del reggaeton moderno con influencias de pop.'),
('Karol G', 'Colombia', 'Reggaeton', 'Carolina Giraldo Navarro, principal representante femenina del reggaeton.'),
('Luis Miguel', 'México', 'Balada', 'El "Sol de México", icono de la música romántica en español.'),
('Thalía', 'México', 'Pop Latino', 'Ariadna Thalía Sodi Miranda, reina del pop latino y actriz.'),
('Café Tacvba', 'México', 'Rock Alternativo', 'Banda innovadora que fusiona rock con ritmos mexicanos tradicionales.'),
('Natalia Lafourcade', 'México', 'Indie Folk', 'Cantautora conocida por rescatar y modernizar la música tradicional mexicana.'),
('Maná', 'México', 'Rock en Español', 'Banda de rock pionera con mensajes ecológicos y sociales.'),
('Rosalía', 'España', 'Flamenco Urbano', 'Rosalía Vila Tobella, fusiona flamenco tradicional con sonidos urbanos y pop.'),
('Alejandro Sanz', 'España', 'Pop', 'Alejandro Sánchez Pizarro, cantautor con 25 Grammy Latinos.'),
('Enrique Iglesias', 'España', 'Pop Latino', 'Uno de los artistas latinos más vendidos con más de 180 millones de discos.'),
('Gustavo Cerati', 'Argentina', 'Rock Argentino', 'Líder de Soda Stereo, icono del rock en español. Músico innovador.'),
('Anitta', 'Brasil', 'Funk Brasileño', 'Larissa de Macedo Machado, popularizó el funk brasileño internacionalmente.');

-- ================================
-- INSERCIONES COMPLETAS DE ÁLBUMES
-- ================================

-- Álbumes de Bad Bunny (ID 1)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Un Verano Sin Ti', 1, 2022, 'Reggaeton', 'un_verano_sin_ti.jpg'),
('YHLQMDLG', 1, 2020, 'Reggaeton', 'yhlqmdlg.jpg'),
('X 100PRE', 1, 2018, 'Reggaeton', 'x100pre.jpg');

-- Álbumes de Daddy Yankee (ID 2)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Barrio Fino', 2, 2004, 'Reggaeton', 'barrio_fino.jpg'),
('Prestige', 2, 2012, 'Reggaeton', 'prestige.jpg'),
('El Cartel: The Big Boss', 2, 2007, 'Reggaeton', 'el_cartel.jpg');

-- Álbumes de J Balvin (ID 3)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Colores', 3, 2020, 'Reggaeton', 'colores.jpg'),
('Vibras', 3, 2018, 'Reggaeton', 'vibras.jpg'),
('Energía', 3, 2016, 'Reggaeton', 'energia.jpg');

-- Álbumes de Marc Anthony (ID 4)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Todo a Su Tiempo', 4, 1995, 'Salsa', 'todo_tiempo.jpg'),
('Contra la Corriente', 4, 1997, 'Salsa', 'contra_corriente.jpg'),
('Libre', 4, 2001, 'Salsa', 'libre.jpg');

-- Álbumes de Celia Cruz (ID 5)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Celia y Johnny', 5, 1974, 'Salsa', 'celia_johnny.jpg'),
('The Best', 5, 1997, 'Salsa', 'the_best.jpg'),
('La Negra Tiene Tumbao', 5, 2001, 'Salsa', 'negra_tumbao.jpg');

-- Álbumes de Shakira (ID 6)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Fijación Oral Vol. 1', 6, 2005, 'Pop Latino', 'fijacion_oral.jpg'),
('El Dorado', 6, 2017, 'Pop Latino', 'el_dorado.jpg'),
('Sale el Sol', 6, 2010, 'Pop Latino', 'sale_el_sol.jpg');

-- Álbumes de Carlos Vives (ID 7)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Corazón Profundo', 7, 2013, 'Vallenato', 'corazon_profundo.jpg'),
('Más Corazón Profundo', 7, 2014, 'Vallenato', 'mas_corazon.jpg'),
('Clásicos de la Provincia', 7, 1993, 'Vallenato', 'clasicos_provincia.jpg');

-- Álbumes de Juanes (ID 8)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Mi Sangre', 8, 2004, 'Rock Latino', 'mi_sangre.jpg'),
('La Vida... Es Un Ratico', 8, 2007, 'Rock Latino', 'vida_ratico.jpg'),
('Un Día Normal', 8, 2002, 'Rock Latino', 'dia_normal.jpg');

-- Álbumes de Maluma (ID 9)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Pretty Boy, Dirty Boy', 9, 2015, 'Reggaeton', 'pretty_boy.jpg'),
('F.A.M.E.', 9, 2018, 'Reggaeton', 'fame.jpg'),
('11:11', 9, 2019, 'Reggaeton', 'once_once.jpg');

-- Álbumes de Karol G (ID 10)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Ocean', 10, 2019, 'Reggaeton', 'ocean.jpg'),
('KG0516', 10, 2021, 'Reggaeton', 'kg0516.jpg'),
('Mañana Será Bonito', 10, 2023, 'Reggaeton', 'manana_bonito.jpg');

-- Álbumes de Luis Miguel (ID 11)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Romance', 11, 1991, 'Balada', 'romance.jpg'),
('Amarte Es Un Placer', 11, 1999, 'Balada', 'amarte_placer.jpg'),
('Segundo Romance', 11, 1994, 'Balada', 'segundo_romance.jpg');

-- Álbumes de Thalía (ID 12)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('En Éxtasis', 12, 1995, 'Pop Latino', 'en_extasis.jpg'),
('Amor a la Mexicana', 12, 1997, 'Pop Latino', 'amor_mexicana.jpg'),
('Arrasando', 12, 2000, 'Pop Latino', 'arrasando.jpg');

-- Álbumes de Café Tacvba (ID 13)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Re', 13, 1994, 'Rock Alternativo', 're.jpg'),
('Avalancha de Éxitos', 13, 1996, 'Rock Alternativo', 'avalancha.jpg'),
('Cuatro Caminos', 13, 2003, 'Rock Alternativo', 'cuatro_caminos.jpg');

-- Álbumes de Natalia Lafourcade (ID 14)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Musas', 14, 2017, 'Indie Folk', 'musas.jpg'),
('Hasta la Raíz', 14, 2015, 'Indie Folk', 'hasta_raiz.jpg'),
('Un Canto por México', 14, 2020, 'Indie Folk', 'canto_mexico.jpg');

-- Álbumes de Maná (ID 15)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('¿Dónde Jugarán Los Niños?', 15, 1992, 'Rock en Español', 'donde_jugaran.jpg'),
('Amar Es Combatir', 15, 2006, 'Rock en Español', 'amar_combatir.jpg'),
('Drama y Luz', 15, 2011, 'Rock en Español', 'drama_luz.jpg');

-- Álbumes de Rosalía (ID 16)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('El Mal Querer', 16, 2018, 'Flamenco Urbano', 'mal_querer.jpg'),
('Motomami', 16, 2022, 'Flamenco Urbano', 'motomami.jpg'),
('Los Ángeles', 16, 2017, 'Flamenco Urbano', 'los_angeles.jpg');

-- Álbumes de Alejandro Sanz (ID 17)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Más', 17, 1997, 'Pop', 'mas.jpg'),
('El Alma al Aire', 17, 2000, 'Pop', 'alma_aire.jpg'),
('No Es lo Mismo', 17, 2003, 'Pop', 'no_es_mismo.jpg');

-- Álbumes de Enrique Iglesias (ID 18)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Euphoria', 18, 2010, 'Pop Latino', 'euphoria.jpg'),
('Sex and Love', 18, 2014, 'Pop Latino', 'sex_love.jpg'),
('Final', 18, 2021, 'Pop Latino', 'final.jpg');

-- Álbumes de Gustavo Cerati (ID 19)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Bocanada', 19, 1999, 'Rock Argentino', 'bocanada.jpg'),
('Siempre Es Hoy', 19, 2002, 'Rock Argentino', 'siempre_hoy.jpg'),
('Fuerza Natural', 19, 2009, 'Rock Argentino', 'fuerza_natural.jpg');

-- Álbumes de Anitta (ID 20)
INSERT INTO albumes (titulo, id_artista, año, genero, portada) VALUES 
('Bang', 20, 2015, 'Funk Brasileño', 'bang.jpg'),
('Kisses', 20, 2019, 'Funk Brasileño', 'kisses.jpg'),
('Versions of Me', 20, 2022, 'Funk Brasileño', 'versions_me.jpg');

-- ==================================
-- INSERCIONES COMPLETAS DE CANCIONES
-- ==================================

-- Canciones de Bad Bunny - Un Verano Sin Ti (Álbum ID: 1)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Moscow Mule', 1, 1, '04:05', 'Reggaeton'),
('Después de la Playa', 1, 1, '03:50', 'Reggaeton'),
('Tití Me Preguntó', 1, 1, '04:03', 'Reggaeton');

-- Canciones de Bad Bunny - YHLQMDLG (Álbum ID: 2)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Si Veo a Tu Mamá', 2, 1, '02:50', 'Reggaeton'),
('La Difícil', 2, 1, '02:43', 'Reggaeton'),
('Yo Perreo Sola', 2, 1, '02:52', 'Reggaeton');

-- Canciones de Bad Bunny - X 100PRE (Álbum ID: 3)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Ni Bien Ni Mal', 3, 1, '03:56', 'Reggaeton'),
('Caro', 3, 1, '03:49', 'Reggaeton'),
('Solo de Mí', 3, 1, '03:17', 'Reggaeton');

-- Canciones de Daddy Yankee - Barrio Fino (Álbum ID: 4)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Gasolina', 4, 2, '03:12', 'Reggaeton'),
('Lo Que Pasó, Pasó', 4, 2, '03:30', 'Reggaeton'),
('King Daddy', 4, 2, '03:17', 'Reggaeton');

-- Canciones de Daddy Yankee - Prestige (Álbum ID: 5)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Limbo', 5, 2, '03:44', 'Reggaeton'),
('Pasarela', 5, 2, '03:13', 'Reggaeton'),
('La Noche de Los Dos', 5, 2, '03:43', 'Reggaeton');

-- Canciones de Daddy Yankee - El Cartel (Álbum ID: 6)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Impacto', 6, 2, '03:05', 'Reggaeton'),
('Ella Me Levantó', 6, 2, '03:29', 'Reggaeton'),
('Pose', 6, 2, '02:35', 'Reggaeton');

-- Canciones de J Balvin - Colores (Álbum ID: 7)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Rojo', 7, 3, '02:30', 'Reggaeton'),
('Amarillo', 7, 3, '02:37', 'Reggaeton'),
('Blanco', 7, 3, '02:25', 'Reggaeton');

-- Canciones de J Balvin - Vibras (Álbum ID: 8)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Mi Gente', 8, 3, '03:05', 'Reggaeton'),
('Ambiente', 8, 3, '04:08', 'Reggaeton'),
('Peligrosa', 8, 3, '03:21', 'Reggaeton');

-- Canciones de J Balvin - Energía (Álbum ID: 9)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Ginza', 9, 3, '02:51', 'Reggaeton'),
('Bobo', 9, 3, '03:29', 'Reggaeton'),
('Safari', 9, 3, '03:23', 'Reggaeton');

-- Canciones de Marc Anthony - Todo a Su Tiempo (Álbum ID: 10)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Te Conozco Bien', 10, 4, '04:09', 'Salsa'),
('Se Me Sigue Olvidando', 10, 4, '04:26', 'Salsa'),
('Hasta Ayer', 10, 4, '04:26', 'Salsa');

-- Canciones de Marc Anthony - Contra la Corriente (Álbum ID: 11)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Y Hubo Alguien', 11, 4, '06:26', 'Salsa'),
('Contra la Corriente', 11, 4, '05:03', 'Salsa'),
('No Me Conoces', 11, 4, '05:04', 'Salsa');

-- Canciones de Marc Anthony - Libre (Álbum ID: 12)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Celos', 12, 4, '04:47', 'Salsa'),
('Barco a la Deriva', 12, 4, '04:23', 'Salsa'),
('Hasta Que Vuelvas Conmigo', 12, 4, '04:51', 'Salsa');

-- Canciones de Celia Cruz - Celia y Johnny (Álbum ID: 13)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Quimbara', 13, 5, '04:53', 'Salsa'),
('Toro Mata', 13, 5, '05:07', 'Salsa'),
('Cúcala', 13, 5, '03:59', 'Salsa');

-- Canciones de Celia Cruz - The Best (Álbum ID: 14)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('La Vida Es Un Carnaval', 14, 5, '04:38', 'Salsa'),
('La Negra Tiene Tumbao', 14, 5, '04:00', 'Salsa'),
('Rie y Llora', 14, 5, '04:10', 'Salsa');

-- Canciones de Celia Cruz - La Negra Tiene Tumbao (Álbum ID: 15)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Azúcar Negra', 15, 5, '03:09', 'Salsa'),
('Hay Que Empezar Otra Vez', 15, 5, '04:26', 'Salsa'),
('Dios Disfrute a la Reina', 15, 5, '04:33', 'Salsa');

-- Canciones de Shakira - Fijación Oral Vol. 1 (Álbum ID: 16)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('La Tortura', 16, 6, '03:32', 'Pop Latino'),
('No', 16, 6, '04:45', 'Pop Latino'),
('Día de Enero', 16, 6, '02:53', 'Pop Latino');

-- Canciones de Shakira - El Dorado (Álbum ID: 17)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Chantaje', 17, 6, '03:15', 'Pop Latino'),
('Me Enamoré', 17, 6, '03:46', 'Pop Latino'),
('Perro Fiel', 17, 6, '03:15', 'Pop Latino');

-- Canciones de Shakira - Sale el Sol (Álbum ID: 18)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Loca', 18, 6, '03:05', 'Pop Latino'),
('Sale el Sol', 18, 6, '03:20', 'Pop Latino'),
('Antes de las Seis', 18, 6, '02:55', 'Pop Latino');

-- Canciones de Carlos Vives - Corazón Profundo (Álbum ID: 19)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Volví a Nacer', 19, 7, '03:42', 'Vallenato'),
('Como Le Gusta a Tu Cuerpo', 19, 7, '03:49', 'Vallenato'),
('Bombillú', 19, 7, '03:54', 'Vallenato');

-- Canciones de Carlos Vives - Más Corazón Profundo (Álbum ID: 20)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('La Foto de los Dos', 20, 7, '03:30', 'Vallenato'),
('Cuando Nos Volvamos a Encontrar', 20, 7, '04:02', 'Vallenato'),
('El Mar de Sus Ojos', 20, 7, '03:42', 'Vallenato');

-- Canciones de Carlos Vives - Clásicos de la Provincia (Álbum ID: 21)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('La Gota Fría', 21, 7, '03:35', 'Vallenato'),
('Alicia Adorada', 21, 7, '04:04', 'Vallenato'),
('Pa Mayte', 21, 7, '04:07', 'Vallenato');

-- Canciones de Juanes - Mi Sangre (Álbum ID: 22)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('La Camisa Negra', 22, 8, '03:36', 'Rock Latino'),
('Para Tu Amor', 22, 8, '04:09', 'Rock Latino'),
('Lo Que Me Gusta a Mí', 22, 8, '03:30', 'Rock Latino');

-- Canciones de Juanes - La Vida... Es Un Ratico (Álbum ID: 23)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Me Enamora', 23, 8, '03:14', 'Rock Latino'),
('Gotas de Agua Dulce', 23, 8, '03:57', 'Rock Latino'),
('Tú y Yo', 23, 8, '04:47', 'Rock Latino');

-- Canciones de Juanes - Un Día Normal (Álbum ID: 24)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('A Dios le Pido', 24, 8, '03:25', 'Rock Latino'),
('Es Por Ti', 24, 8, '04:11', 'Rock Latino'),
('Mala Gente', 24, 8, '03:15', 'Rock Latino');

-- Canciones de Maluma - Pretty Boy, Dirty Boy (Álbum ID: 25)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Borró Cassette', 25, 9, '03:27', 'Reggaeton'),
('El Perdedor', 25, 9, '03:31', 'Reggaeton'),
('Sin Contrato', 25, 9, '04:12', 'Reggaeton');

-- Canciones de Maluma - F.A.M.E. (Álbum ID: 26)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Felices los 4', 26, 9, '03:50', 'Reggaeton'),
('Corazón', 26, 9, '03:05', 'Reggaeton'),
('El Préstamo', 26, 9, '03:39', 'Reggaeton');

-- Canciones de Maluma - 11:11 (Álbum ID: 27)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('HP', 27, 9, '03:05', 'Reggaeton'),
('No Se Me Quita', 27, 9, '04:11', 'Reggaeton'),
('Dispuesto', 27, 9, '03:05', 'Reggaeton');

-- Canciones de Karol G - Ocean (Álbum ID: 28)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Pineapple', 28, 10, '03:35', 'Reggaeton'),
('Culpables', 28, 10, '03:46', 'Reggaeton'),
('Créeme', 28, 10, '03:33', 'Reggaeton');

-- Canciones de Karol G - KG0516 (Álbum ID: 29)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Bichota', 29, 10, '02:58', 'Reggaeton'),
('Location', 29, 10, '04:23', 'Reggaeton'),
('200 Copas', 29, 10, '03:18', 'Reggaeton');

-- Canciones de Karol G - Mañana Será Bonito (Álbum ID: 30)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Provenza', 30, 10, '03:30', 'Reggaeton'),
('Cairo', 30, 10, '03:21', 'Reggaeton'),
('Gatúbela', 30, 10, '03:29', 'Reggaeton');

-- Canciones de Luis Miguel - Romance (Álbum ID: 31)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Inolvidable', 31, 11, '03:55', 'Balada'),
('No Sé Tú', 31, 11, '03:49', 'Balada'),
('La Barca', 31, 11, '03:30', 'Balada');

-- Canciones de Luis Miguel - Amarte Es Un Placer (Álbum ID: 32)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('O Tú o Ninguna', 32, 11, '03:16', 'Balada'),
('Sol, Arena y Mar', 32, 11, '03:44', 'Balada'),
('Dormir Contigo', 32, 11, '03:25', 'Balada');

-- Canciones de Luis Miguel - Segundo Romance (Álbum ID: 33)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('El Día Que Me Quieras', 33, 11, '04:00', 'Balada'),
('La Media Vuelta', 33, 11, '02:42', 'Balada'),
('Delirio', 33, 11, '03:56', 'Balada');

-- Canciones de Thalía - En Éxtasis (Álbum ID: 34)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Piel Morena', 34, 12, '04:42', 'Pop Latino'),
('María la del Barrio', 34, 12, '03:53', 'Pop Latino'),
('Gracias a Dios', 34, 12, '04:00', 'Pop Latino');

-- Canciones de Thalía - Amor a la Mexicana (Álbum ID: 35)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Amor a la Mexicana', 35, 12, '04:25', 'Pop Latino'),
('Mujer Latina', 35, 12, '03:38', 'Pop Latino'),
('Noches Sin Luna', 35, 12, '04:08', 'Pop Latino');

-- Canciones de Thalía - Arrasando (Álbum ID: 36)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Arrasando', 36, 12, '04:00', 'Pop Latino'),
('Entre el Mar y una Estrella', 36, 12, '03:45', 'Pop Latino'),
('Regresa a Mí', 36, 12, '04:29', 'Pop Latino');

-- Canciones de Café Tacvba - Re (Álbum ID: 37)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('La Ingrata', 37, 13, '03:32', 'Rock Alternativo'),
('Eres', 37, 13, '04:28', 'Rock Alternativo'),
('La Locomotora', 37, 13, '03:41', 'Rock Alternativo');

-- Canciones de Café Tacvba - Avalancha de Éxitos (Álbum ID: 38)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Cómo Te Extraño', 38, 13, '03:34', 'Rock Alternativo'),
('Chilanga Banda', 38, 13, '03:32', 'Rock Alternativo'),
('No Controles', 38, 13, '03:05', 'Rock Alternativo');

-- Canciones de Café Tacvba - Cuatro Caminos (Álbum ID: 39)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Eo', 39, 13, '03:29', 'Rock Alternativo'),
('Cero y Uno', 39, 13, '03:46', 'Rock Alternativo'),
('Qué Pasó?', 39, 13, '03:29', 'Rock Alternativo');

-- Canciones de Natalia Lafourcade - Musas (Álbum ID: 40)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Tú Sí Sabes Quererme', 40, 14, '04:04', 'Indie Folk'),
('Soledad y el Mar', 40, 14, '03:35', 'Indie Folk'),
('Te Vi Pasar', 40, 14, '03:41', 'Indie Folk');

-- Canciones de Natalia Lafourcade - Hasta la Raíz (Álbum ID: 41)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Hasta la Raíz', 41, 14, '03:41', 'Indie Folk'),
('Nunca Es Suficiente', 41, 14, '03:57', 'Indie Folk'),
('Lo Que Construimos', 41, 14, '04:39', 'Indie Folk');

-- Canciones de Natalia Lafourcade - Un Canto por México (Álbum ID: 42)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('La Llorona', 42, 14, '04:54', 'Indie Folk'),
('Cucurrucucú Paloma', 42, 14, '05:52', 'Indie Folk'),
('Verde de Verdad', 42, 14, '03:14', 'Indie Folk');

-- Canciones de Maná - ¿Dónde Jugarán Los Niños? (Álbum ID: 43)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Oye Mi Amor', 43, 15, '04:18', 'Rock en Español'),
('Como Te Deseo', 43, 15, '04:26', 'Rock en Español'),
('Te Solté la Rienda', 43, 15, '04:02', 'Rock en Español');

-- Canciones de Maná - Amar Es Combatir (Álbum ID: 44)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Labios Compartidos', 44, 15, '04:00', 'Rock en Español'),
('Bendita Tu Luz', 44, 15, '04:24', 'Rock en Español'),
('Manda una Señal', 44, 15, '03:54', 'Rock en Español');

-- Canciones de Maná - Drama y Luz (Álbum ID: 45)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Lluvia al Corazón', 45, 15, '04:16', 'Rock en Español'),
('Amor Clandestino', 45, 15, '04:52', 'Rock en Español'),
('El Espejo', 45, 15, '04:54', 'Rock en Español');

-- Canciones de Rosalía - El Mal Querer (Álbum ID: 46)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Malamente', 46, 16, '02:30', 'Flamenco Urbano'),
('Pienso en Tu Mirá', 46, 16, '03:23', 'Flamenco Urbano'),
('Di Mi Nombre', 46, 16, '02:42', 'Flamenco Urbano');

-- Canciones de Rosalía - Motomami (Álbum ID: 47)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Saoko', 47, 16, '02:10', 'Flamenco Urbano'),
('Candy', 47, 16, '03:13', 'Flamenco Urbano'),
('La Fama', 47, 16, '03:08', 'Flamenco Urbano');

-- Canciones de Rosalía - Los Ángeles (Álbum ID: 48)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Si Tú Supieras Compañero', 48, 16, '03:16', 'Flamenco Urbano'),
('De Plata', 48, 16, '03:22', 'Flamenco Urbano'),
('I See a Darkness', 48, 16, '03:56', 'Flamenco Urbano');

-- Canciones de Alejandro Sanz - Más (Álbum ID: 49)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Corazón Partío', 49, 17, '05:48', 'Pop'),
('Amiga Mía', 49, 17, '04:50', 'Pop'),
('Si Tú Me Miras', 49, 17, '04:17', 'Pop');

-- Canciones de Alejandro Sanz - El Alma al Aire (Álbum ID: 50)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('El Alma al Aire', 50, 17, '04:35', 'Pop'),
('Cuando Nadie Me Ve', 50, 17, '04:22', 'Pop'),
('Quisiera Ser', 50, 17, '05:31', 'Pop');

-- Canciones de Alejandro Sanz - No Es lo Mismo (Álbum ID: 51)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('No Es lo Mismo', 51, 17, '05:59', 'Pop'),
('He Sido Tan Feliz Contigo', 51, 17, '04:27', 'Pop'),
('Regálame la Silla Donde Te Esperé', 51, 17, '04:07', 'Pop');

-- Canciones de Enrique Iglesias - Euphoria (Álbum ID: 52)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Cuando Me Enamoro', 52, 18, '03:20', 'Pop Latino'),
('I Like It', 52, 18, '03:52', 'Pop Latino'),
('Heartbeat', 52, 18, '04:17', 'Pop Latino');

-- Canciones de Enrique Iglesias - Sex and Love (Álbum ID: 53)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Bailando', 53, 18, '04:03', 'Pop Latino'),
('El Perdedor', 53, 18, '03:15', 'Pop Latino'),
('Loco', 53, 18, '03:50', 'Pop Latino');

-- Canciones de Enrique Iglesias - Final (Álbum ID: 54)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Duele el Corazón', 54, 18, '03:20', 'Pop Latino'),
('Súbeme la Radio', 54, 18, '03:28', 'Pop Latino'),
('El Baño', 54, 18, '03:48', 'Pop Latino');

-- Canciones de Gustavo Cerati - Bocanada (Álbum ID: 55)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Puente', 55, 19, '04:55', 'Rock Argentino'),
('Paseo Inmoral', 55, 19, '03:42', 'Rock Argentino'),
('Raíz', 55, 19, '03:54', 'Rock Argentino');

-- Canciones de Gustavo Cerati - Siempre Es Hoy (Álbum ID: 56)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Karaoke', 56, 19, '03:33', 'Rock Argentino'),
('Artefacto', 56, 19, '04:13', 'Rock Argentino'),
('Cosas Imposibles', 56, 19, '04:28', 'Rock Argentino');

-- Canciones de Gustavo Cerati - Fuerza Natural (Álbum ID: 57)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Deja Vu', 57, 19, '04:25', 'Rock Argentino'),
('Fuerza Natural', 57, 19, '04:25', 'Rock Argentino'),
('Cactus', 57, 19, '03:54', 'Rock Argentino');

-- Canciones de Anitta - Bang (Álbum ID: 58)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Bang', 58, 20, '03:10', 'Funk Brasileño'),
('Deixa Ele Sofrer', 58, 20, '02:51', 'Funk Brasileño'),
('Na Batida', 58, 20, '02:31', 'Funk Brasileño');

-- Canciones de Anitta - Kisses (Álbum ID: 59)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Rosa', 59, 20, '02:56', 'Funk Brasileño'),
('Poquito', 59, 20, '02:43', 'Funk Brasileño'),
('Meu Mel', 59, 20, '02:28', 'Funk Brasileño');

-- Canciones de Anitta - Versions of Me (Álbum ID: 60)
INSERT INTO canciones (titulo, id_album, id_artista, duracion, genero) VALUES 
('Envolver', 60, 20, '03:13', 'Funk Brasileño'),
('Gata', 60, 20, '02:54', 'Funk Brasileño'),
('Boys Don''t Cry', 60, 20, '02:16', 'Funk Brasileño');