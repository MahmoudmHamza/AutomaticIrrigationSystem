--====================================================
-- Create tables for automatic irrigation system
--====================================================
drop table if exists irrigation_slots;
CREATE TABLE irrigation_slots (
  id SERIAL PRIMARY KEY,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  slot_status VARCHAR(20) NOT NULL
);

drop table if exists plot_configurations;
CREATE TABLE plot_configurations (
  id SERIAL PRIMARY KEY,
  irrigation_slot_id BIGINT REFERENCES irrigation_slots(id),
  water_amount DECIMAL(10, 2) NOT NULL
);

drop table if exists plots;
CREATE TABLE plots (
  id SERIAL PRIMARY KEY,
  plot_name VARCHAR(255) NOT NULL,
  plot_area DECIMAL(10, 2) NOT NULL,
  plot_configuration_id BIGINT REFERENCES plot_configurations(id)
);

drop table if exists sensors;
CREATE TABLE sensors (
  id SERIAL PRIMARY KEY,
  plot_id BIGINT REFERENCES plots(id),
  sensor_status VARCHAR(20) NOT NULL
);

--====================================================
-- Insert seed data for automatic irrigation system
--====================================================
INSERT INTO irrigation_slots (start_time, end_time, slot_status) VALUES
('08:00:00', '10:00:00', 'IN_PROGRESS'),
('10:00:00', '12:00:00', 'IN_PROGRESS'),
('12:00:00', '14:00:00', 'IN_PROGRESS');

INSERT INTO plot_configurations (irrigation_slot_id, water_amount) VALUES
(1, 150),
(2, 500),
(3, 325);

INSERT INTO plots (plot_name, plot_area, plot_configuration_id) VALUES
('Plot A', 450.65, 1),
('Plot B', 875.00, 2),
('Plot C', 970.43, 3);

INSERT INTO sensors (plot_id, sensor_status) VALUES
(1, 'ACTIVE'),
(2, 'ACTIVE'),
(3, 'INACTIVE');

