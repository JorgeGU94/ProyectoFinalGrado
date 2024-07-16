DROP DATABASE IF EXISTS tiendaTattoos;
CREATE DATABASE IF NOT EXISTS tiendaTattoos;
use tiendaTattoos; 

-- Crear la tabla de Clientes
CREATE TABLE `clientes` (
  `ID_Clientes` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `Telefono` VARCHAR(9) NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NULL,
  PRIMARY KEY (`ID_Clientes`));
  
-- Crear la tabla de Tatuadores
CREATE TABLE `tatuadores` (
  `ID_Tatuadores` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Especialidad` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(1000) NULL,
  `Activo` INT NOT NULL,
  PRIMARY KEY (`ID_Tatuadores`));

-- Crear la tabla de Tatuajes
CREATE TABLE `tatuajes` (
  `ID_Tatuajes` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(45) NULL,
  `Color` INT NULL,
  PRIMARY KEY (`ID_Tatuajes`));
  
ALTER TABLE `tiendatattoos`.`tatuajes` 
ADD COLUMN `Tamano` decimal(10,2) NULL AFTER `Color`,
ADD COLUMN `Precio` decimal(10,2) NULL AFTER `Tamano`;

  
  -- Crear la tabla de Citas
  CREATE TABLE `citas` (
  `ID_Citas` INT NOT NULL AUTO_INCREMENT,
  `Fecha` VARCHAR(45) NOT NULL,
  `Activo` INT NOT NULL,
  `ID_Clientes` INT NOT NULL,
  `ID_Tatuadores` INT NOT NULL,
  `ID_Tatuajes` INT NOT NULL,
  PRIMARY KEY (`ID_Citas`),
  INDEX `ID_Clientes_idx` (`ID_Clientes` ASC) VISIBLE,
  INDEX `ID_Tatuadores_idx` (`ID_Tatuadores` ASC) VISIBLE,
  INDEX `ID_Tatuajes_idx` (`ID_Tatuajes` ASC) VISIBLE,
  CONSTRAINT `ID_Clientes`
    FOREIGN KEY (`ID_Clientes`)
    REFERENCES `tiendatattoos`.`clientes` (`ID_Clientes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ID_Tatuadores`
    FOREIGN KEY (`ID_Tatuadores`)
    REFERENCES `tiendatattoos`.`tatuadores` (`ID_Tatuadores`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ID_Tatuajes`
    FOREIGN KEY (`ID_Tatuajes`)
    REFERENCES `tiendatattoos`.`tatuajes` (`ID_Tatuajes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
  -- Crear la tabla de Imagenes
CREATE TABLE `tiendatattoos`.`imagenestatus` (
  `ID_Imagen` INT NOT NULL AUTO_INCREMENT,
  `ID_Tatuadores` INT NOT NULL,
  `Descripcion` VARCHAR(45) NULL,
  `Imagenes` VARCHAR(2000) NULL,
  PRIMARY KEY (`ID_Imagen`),
  INDEX `ID_Tatuadores_idx` (`ID_Tatuadores` ASC) VISIBLE,
  CONSTRAINT `ID_Tatuadores_Imagen`
    FOREIGN KEY (`ID_Tatuadores`)
    REFERENCES `tiendatattoos`.`tatuadores` (`ID_Tatuadores`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

INSERT INTO clientes (Nombre, Apellidos, Telefono, Email, Password)
VALUES
    ('Juan', 'García', '123456789', 'juangarcia@example.com', 'clave123'),
    ('María', 'López', '159752346', 'marialopez@example.com', 'password456'),
    ('Pedro', 'Martínez', '987654321', 'pedromartinez@example.com', 'mipass');
    
INSERT INTO tatuadores (Nombre, Apellidos, Email, Especialidad, Descripcion, Activo)
VALUES
    ('Ana', 'Rodríguez', 'anarodriguez@example.com', 'Aquarela', 'Ana Rodríguez es una talentosa tatuadora especializada en el estilo de acuarela. Con más de 10 años de experiencia en el mundo del tatuaje, Ana ha perfeccionado su técnica para crear obras de arte vibrantes y llenas de vida, que parecen pintadas directamente sobre la piel. Su pasión por los colores y su habilidad para crear transiciones suaves y efectos de degradado hacen que sus tatuajes sean verdaderamente únicos y apreciados por sus clientes.Ana se inspira en la naturaleza y el arte contemporáneo, fusionando ambos mundos para ofrecer diseños personalizados que capturan la esencia y personalidad de cada cliente. Su atención meticulosa al detalle y su compromiso con la calidad aseguran que cada tatuaje no solo sea hermoso, sino también duradero y realizado con los más altos estándares de higiene y seguridad. ', 1),
    ('Carlos', 'Gómez', 'carlosgomez@example.com', 'Blackwork', 'Carlos Gómez es un destacado tatuador especializado en el estilo de Blackwork. Con una carrera que abarca una década, Carlos ha desarrollado una maestría única en el uso de tinta negra para crear tatuajes impactantes y duraderos. Su enfoque se caracteriza por la precisión en los detalles y la profundidad de los contrastes, logrando piezas que van desde patrones geométricos y mandalas hasta escenas complejas y abstractas. Carlos encuentra su inspiración en el arte tribal, el diseño contemporáneo y la naturaleza, fusionando estos elementos para ofrecer tatuajes que son tanto poderosos como elegantes. Su habilidad para trabajar con sombras, líneas gruesas y finas, y espacios negativos permite que cada tatuaje sea una obra de arte personalizada, adaptada a la visión y deseos de cada cliente.', 1),
    ('Sofía', 'Hernández', 'sofiahernandez@example.com', 'Realismo', 'Sofía Hernández es una talentosa tatuadora especializada en el estilo de Realismo. Con 15 años de experiencia en el arte del tatuaje, Sofía ha desarrollado una habilidad excepcional para capturar la esencia de sus sujetos con una precisión asombrosa. Su dominio de los detalles y su capacidad para crear sombras y texturas realistas hacen que sus tatuajes parezcan cobrar vida en la piel. Inspirada por la belleza de la vida cotidiana y las complejidades del mundo natural, Sofía se dedica a transformar fotografías y recuerdos en impresionantes obras de arte. Desde retratos detallados hasta escenas complejas, su trabajo refleja una profunda comprensión de la luz, la sombra y la proporción, resultando en piezas que son tan realistas que parecen salirse del cuerpo. ', 0);

INSERT INTO tatuajes (Descripcion, Color, Tamano, Precio)
VALUES
    ('Flor', 1, 15.50, 100.00),
    ('Dragón', 0, 25.75, 150.00),
    ('Calavera', 1, 20.00, 120.00);
INSERT INTO tatuajes (Descripcion, Color, Tamano, Precio)
VALUES
    ('Flor', 1, 15.50, 100.00),
    ('Dragón', 1, 25.75, 150.00),
    ('Calavera', 1, 20.00, 120.00);
INSERT INTO imagenestatus (ID_Tatuadores, Descripcion, Imagenes)
VALUES
    (1, 'PixelPoder', 'iVBORw0KGgoAAAANSUhEUgAAAAIAAAACCAYAAABytg0kAAAAFklEQVR42mP8z/D/PwMDAwMjjAEAbYQF/x6W+AAAAABJRU5ErkJggg=='),
    (2, 'Emoji', 'iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAApgAAAKYB3X3/OAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAANCSURBVEiJtZZPbBtFFMZ/M7ubXdtdb1xSFyeilBapySVU8h8OoFaooFSqiihIVIpQBKci6KEg9Q6H9kovIHoCIVQJJCKE1ENFjnAgcaSGC6rEnxBwA04Tx43t2FnvDAfjkNibxgHxnWb2e/u992bee7tCa00YFsffekFY+nUzFtjW0LrvjRXrCDIAaPLlW0nHL0SsZtVoaF98mLrx3pdhOqLtYPHChahZcYYO7KvPFxvRl5XPp1sN3adWiD1ZAqD6XYK1b/dvE5IWryTt2udLFedwc1+9kLp+vbbpoDh+6TklxBeAi9TL0taeWpdmZzQDry0AcO+jQ12RyohqqoYoo8RDwJrU+qXkjWtfi8Xxt58BdQuwQs9qC/afLwCw8tnQbqYAPsgxE1S6F3EAIXux2oQFKm0ihMsOF71dHYx+f3NND68ghCu1YIoePPQN1pGRABkJ6Bus96CutRZMydTl+TvuiRW1m3n0eDl0vRPcEysqdXn+jsQPsrHMquGeXEaY4Yk4wxWcY5V/9scqOMOVUFthatyTy8QyqwZ+kDURKoMWxNKr2EeqVKcTNOajqKoBgOE28U4tdQl5p5bwCw7BWquaZSzAPlwjlithJtp3pTImSqQRrb2Z8PHGigD4RZuNX6JYj6wj7O4TFLbCO/Mn/m8R+h6rYSUb3ekokRY6f/YukArN979jcW+V/S8g0eT/N3VN3kTqWbQ428m9/8k0P/1aIhF36PccEl6EhOcAUCrXKZXXWS3XKd2vc/TRBG9O5ELC17MmWubD2nKhUKZa26Ba2+D3P+4/MNCFwg59oWVeYhkzgN/JDR8deKBoD7Y+ljEjGZ0sosXVTvbc6RHirr2reNy1OXd6pJsQ+gqjk8VWFYmHrwBzW/n+uMPFiRwHB2I7ih8ciHFxIkd/3Omk5tCDV1t+2nNu5sxxpDFNx+huNhVT3/zMDz8usXC3ddaHBj1GHj/As08fwTS7Kt1HBTmyN29vdwAw+/wbwLVOJ3uAD1wi/dUH7Qei66PfyuRj4Ik9is+hglfbkbfR3cnZm7chlUWLdwmprtCohX4HUtlOcQjLYCu+fzGJH2QRKvP3UNz8bWk1qMxjGTOMThZ3kvgLI5AzFfo379UAAAAASUVORK5CYII=');
INSERT INTO citas (Fecha, Activo, ID_Clientes, ID_Tatuadores, ID_Tatuajes)
VALUES
    ('2024-07-01', 1, 1, 2, 3),
    ('2024-07-05', 1, 2, 3, 1),
    ('2024-07-10', 1, 3, 1, 2);

