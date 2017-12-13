(define (problem problem2)
  (:domain domain1)

  (:objects
        earth Mars Moon Pluto Mercury Jupiter planetz lv-426 planet-xyz hostileplanet1 hostileplanet2 unknownplanet - planet
        Normandy - ship
        
        captain - captain
        engineer - engineer 
        science-officer - science-officer
        navigator - navigator 
        medical-personnel - medical-personnel 
        security-staff - security-staff
        transporter-chief - transporter-chief
        robot1 - robot
        
        bridge - bridge
        engineering - engineering
        sickbay - sickbay
        transporter-room - transporter-room
        shuttle-bay - shuttle-bay
        science-lab - science-lab
        cargo-bay - cargo-bay
        
       	rover1 rover2 rover3 comstation1 comstation2 comstation3 - heavy
        plasma1 - plasma
        rock1 plasma2 medical1 medical2 medical3 medical4 medical5 - light

        lift1 lift2 lift3 - lift
        hall1 hall2 - hall
  )
  
  (:init
    (path lift1 lift2) (path lift2 lift1) (path lift2 lift3) (path lift3 lift2)
    (path lift1 hall1) (path hall1 lift1) (path lift2 hall2) (path hall2 lift2)
    (path hall1 cargo-bay) (path cargo-bay hall1) (path hall1 shuttle-bay) (path shuttle-bay hall1) (path hall1 engineering) (path engineering hall1)
    (path hall2 sickbay) (path sickbay hall2) (path hall2 science-lab) (path science-lab hall2) (path hall2 transporter-room) (path transporter-room hall2)
    (path lift3 bridge) (path bridge lift3)
    
    (at captain bridge)
    (at engineer bridge)
    (at science-officer bridge)
    (at navigator bridge)
    (at medical-personnel bridge)
    (at security-staff bridge)
    (at transporter-chief bridge)
    
    (at robot1 bridge)
    (robot robot1)
    (charged robot1)
    

    
    (ship-at earth)
    (path earth transporter-room)
    (path transporter-room earth)
    (path shuttle-bay earth)
    (path earth shuttle-bay)
    
    (only-light-equipment earth transporter-room) (only-light-equipment transporter-room earth)
    (only-non-volatile shuttle-bay earth) (only-non-volatile earth shuttle-bay)
    
    (equipment-location rover1 cargo-bay) (rover rover1) (pickable rover1)
    (equipment-location comstation1 cargo-bay) (communication-station comstation1) (pickable comstation1)
    (equipment-location medical1 sickbay) (medical-supplies medical1) (pickable medical1)
    (equipment-location rover2 cargo-bay) (rover rover2) (pickable rover2) 
    (equipment-location comstation2 cargo-bay) (communication-station comstation2) (pickable comstation2)
    (equipment-location medical2 sickbay) (medical-supplies medical2) (pickable medical2)
    (equipment-location rover3 cargo-bay) (rover rover3) (pickable rover3)
    (equipment-location comstation3 cargo-bay) (communication-station comstation3) (pickable comstation3)
    (equipment-location medical3 sickbay) (medical-supplies medical3) (pickable medical3)
(equipment-location medical4 sickbay) (medical-supplies medical4) (pickable medical4)
(equipment-location medical5 sickbay) (medical-supplies medical5) (pickable medical5)


(rock plasma1) (equipment-location plasma1 Mars) (bound plasma1 Mars) (pickable plasma1)
(rock rock1) (equipment-location rock1 lv-426) (bound rock1 lv-426) (pickable rock1)
(rock plasma2) (equipment-location plasma2 planet-xyz) (bound plasma2 planet-xyz) (pickable plasma2)

(asteroid Moon)
    
    (relief-mission Pluto) ; medical supplies to certain planets
    (exploratory-mission Mars) ; new planets
	(exploratory-mission lv-426)
	(exploratory-mission planet-xyz)
    (first-contact-mission Moon) ; new worlds
	(first-contact-mission hostileplanet1)
	(assumed-hostile hostileplanet1)
	(first-contact-mission hostileplanet2)
	(assumed-hostile hostileplanet1)
	(hostile hostileplanet2)
	(first-contact-mission unknownplanet)
	(hostile unknownplanet)
    (relief-mission Jupiter)
    (relief-mission Mars)
    (relief-mission Moon)
    (relief-mission Mercury)
  )
  
  (:goal
  (and
    
   (forall (?x - planet)
     (and
       ;(not (relief-mission ?x))
       (not (exploratory-mission ?x))
       ;(not (first-contact-mission ?x))
       )
  )
  (ship-at earth)
    )
  )
  
)
