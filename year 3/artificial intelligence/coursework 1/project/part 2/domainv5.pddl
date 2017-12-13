(define (domain domain1)
    (:requirements
        :strips
        :typing
        :adl
        :equality
    )
    
    (:types 
    
    bridge engineering sickbay transporter-room shuttle-bay science-lab cargo-bay lift hall planet - static
    
    captain engineer science-officer navigator medical-personnel security-staff transporter-chief robot - personnel
    
    plasma light heavy - equipment
    
    ship
    )
    
        (:predicates
        
        (at ?x - personnel ?y - static)
        (path ?x - static ?y - static)
        
        (asteroid ?x - planet)
        (hostile ?x - planet)
		(assumed-hostile ?x - planet)
        (ship-at ?x - planet)
        (damaged ?x - ship)
        
        (has-order ?y - personnel)
        (injured ?x - personnel)
        
        (only-light-equipment ?x - static ?y - static)
        (only-non-volatile ?x - static ?y - static)
        (damaged-room ?x - static)
        
        (equipment-location ?e - equipment ?x - static)
		
		(pickable ?x - equipment)

        (charged ?r - personnel)
        (robot ?r - personnel)
        
        (medical-supplies ?x - light)
        (rover ?x - heavy)
        (communication-station ?x - heavy)
        (rock ?x - (either light plasma))
		
		; small predicate i used to double check that something belongs to the planet
		; for exploratory missions
		(bound ?x - equipment ?y - planet)
        
        ; personal can only pick-up one at a time
        (is-carrying ?p - personnel)
		; to determine what the personnel is carrying
        (carrying ?p - personnel ?e - equipment)
        
        (relief-mission ?x - planet) ; medical supplies equipment to certain planets
        (exploratory-mission ?x - planet) ; new planets heavy equipment - rover, communication station
        (first-contact-mission ?x - planet) ; new worlds
    )
    
    (:action travel
        :parameters
            (?x ?y - planet ?c - captain ?room - bridge ?transporter - transporter-room ?shuttle - shuttle-bay ?chief - transporter-chief ?s - ship ?n - navigator)
        :precondition
            (and
                (not (= ?x ?y))
                (ship-at ?x)
                (at ?c ?room)
                (has-order ?n)

		(not (injured ?c))
                (not (damaged ?s))
                (at ?n ?room)
                (at ?chief ?transporter)
                
                ; cannot handle the stress test
                (forall (?z - personnel)
                   (and
                        (not (at ?z ?x))
                        (not (at ?z ?y))
                    )
                )
            )
        :effect
            (and
            (not (path ?transporter ?x))
            (not (path ?x ?transporter))
            (not (path ?shuttle ?x))
            (not (path ?x ?shuttle))
            
            (when
                (not (damaged-room ?transporter))
                (and
                    (path ?transporter ?y)
                    (path ?y ?transporter)
                    (has-order ?chief)
                    (only-light-equipment ?y ?transporter)
                    (only-light-equipment ?transporter ?y)
                )
            )

	(when (damaged-room ?transporter)
		(not (has-order ?chief))
	)
            

            (not (only-non-volatile ?shuttle ?x))
            (not (only-non-volatile ?x ?shuttle))
            (only-non-volatile ?shuttle ?y)
            (only-non-volatile ?y ?shuttle)
            
            (not (only-light-equipment ?transporter ?x))
            (not (only-light-equipment ?x ?transporter))

            
            (path ?shuttle ?y)
            (path ?y ?shuttle)
            
            (not (ship-at ?x))
            (ship-at ?y)
            (not (has-order ?n))
            
            (when (asteroid ?y)
                (damaged ?s)
            )
            )
    )
    
    (:action order
        :parameters
            (?x - personnel)
        :precondition
            (not (has-order ?x))
        :effect
            (has-order ?x)
    )

    (:action heal
        :parameters
            (?x - personnel ?medic - medical-personnel ?y - sickbay)
        :precondition
            (and
                (not (robot ?x))
                (injured ?x)
                (at ?x ?y)
                (at ?medic ?y)
            )
        :effect
            (not (injured ?x))
    )
    
    (:action mission-exploration
        :parameters
            (?p - planet ?scientist - science-officer ?lab - science-lab ?rover ?comm-station - heavy ?rock - (either light plasma) )
        :precondition
            (and
                (ship-at ?p)
                (exploratory-mission ?p)
                (at ?scientist ?p)
                (rover ?rover)
                (rock ?rock)
                (communication-station ?comm-station)
                (equipment-location ?rover ?p)
                (equipment-location ?comm-station ?p)
                (equipment-location ?rock ?lab)
				
				(bound ?rock ?p)
            )
        :effect
		(and
            (not (exploratory-mission ?p))
			(not (pickable ?rover))
			(not (pickable ?comm-station))
		)
    )
	
	(:action mission-relief
        :parameters
            (?p - planet ?medic - medical-personnel ?supplies - light)
        :precondition
            (and
                (ship-at ?p)
                (medical-supplies ?supplies)
                (relief-mission ?p)
                (at ?medic ?p)
                (equipment-location ?supplies ?p)
            )
        :effect
		(and
            (not (relief-mission ?p))
			(not (pickable ?supplies))
		)
    )
    
    (:action mission-first-contact
        :parameters
            (?p - planet ?captain - captain ?security - security-staff)
        :precondition
            (and
				(assumed-hostile ?p)
                (ship-at ?p)
                (first-contact-mission ?p)
                (at ?captain ?p)
                (at ?security ?p)
            )
        :effect
        (and
			(when
				(and (hostile ?p) (not (at ?security ?p)))
				(injured ?captain)
			)
            (not (first-contact-mission ?p))
        )
    )
	
	(:action mission-first-contact-2
        :parameters
            (?p - planet ?captain - captain)
        :precondition
            (and
				(not (assumed-hostile ?p))
                (ship-at ?p)
                (first-contact-mission ?p)
                (at ?captain ?p)
            )
        :effect
        (and
		(when
				(hostile ?p)
				(injured ?captain)
		)

            (not (first-contact-mission ?p))
        )
    )
    
    (:action fix-ship
        :parameters
            (?s - ship ?p - engineer ?room - engineering)
        :precondition
            (and
                (damaged ?s)
                (at ?p ?room)
            )
        :effect
            (not (damaged ?s))
    )
    
     (:action recharge
        :parameters
            (?x - robot ?y - science-lab)
        :precondition
            (and
                (not (charged ?x))
                (at ?x ?y)
            )
        :effect
            (charged ?x)
    )
    
    (:action drop
        :parameters
            (?x - personnel ?y - equipment ?room - static)
        :precondition
            (and
                (carrying ?x ?y)
                (at ?x ?room)
            )
        :effect
            (and
                (not (carrying ?x ?y))
                (not (is-carrying ?x))
                (equipment-location ?y ?room)
		
		(when	(robot ?x)
			(not (charged ?x))
		)
            )
    )

	;(:action fix-room
	;	:parameters
	;		(?x - static)
	;	:precondition
	;		(damaged-room ?x)
	;	:effect
	;		(not (damaged-room ?x))
	;)
    
; all paths unlocked.
    (:action move
        :parameters
            (?x - personnel ?r-src ?r-dest - static)
        :precondition
            (and
                (not (has-order ?x))
                (at ?x ?r-src)
                (not (at ?x ?r-dest))
                (path ?r-src ?r-dest)
                (path ?r-dest ?r-src)
                (not (is-carrying ?x))
            )
        :effect
            (and
                (not (at ?x ?r-src))
                (at ?x ?r-dest)
            )
    )
    
	; all paths except for the shuttle to planet path allowed.
    (:action move-with-plasma
        :parameters
            (?x - robot ?e - plasma ?r-src ?r-dest - static)
        :precondition
            (and
                (not (has-order ?x))
                (at ?x ?r-src)
                (is-carrying ?x)
                (carrying ?x ?e)
                
                (not (only-non-volatile ?r-src ?r-dest))
                (not (only-non-volatile ?r-dest ?r-src))
                
                (not (at ?x ?r-dest))
                (path ?r-src ?r-dest)
                (path ?r-dest ?r-src)
            )
        :effect
            (and
                (not (at ?x ?r-src))
		; damaged room isn't really used anywhere. I am just blindly planting the prerequisite
		; in hopes that one of the static objects will be the transporter room
                (damaged-room ?r-dest)
		(damaged-room ?r-src)
                (at ?x ?r-dest)
            )
    )

	; all paths unlocked
    (:action move-with-light-equipment
        :parameters
            (?x - personnel ?e - light ?r-src ?r-dest - static)
        :precondition
            (and
                (not (has-order ?x))
                (at ?x ?r-src)
                (is-carrying ?x)
                (carrying ?x ?e)
                
                (not (at ?x ?r-dest))
                (path ?r-src ?r-dest)
                (path ?r-dest ?r-src)
            )
        :effect
            (and
                (not (at ?x ?r-src))
                (at ?x ?r-dest)
            )
    )
    
    ; heavy = shuttle only
; this action should be able to use any path except for the transporter room to planet path
    (:action move-with-heavy-equipment
        :parameters
            (?x - robot ?e - heavy ?r-src ?r-dest - static)
        :precondition
            (and
                (not (has-order ?x))
                (at ?x ?r-src)
                (is-carrying ?x)
                (carrying ?x ?e)
                
                (not (only-light-equipment ?r-src ?r-dest))
                (not (only-light-equipment ?r-dest ?r-src))
                
                (not (at ?x ?r-dest))
                (path ?r-src ?r-dest)
                (path ?r-dest ?r-src)
            )
        :effect
            (and
                (not (at ?x ?r-src))
                (at ?x ?r-dest)
            )
    )
    
    (:action fix-ship
        :parameters
            (?s - ship ?p - engineer ?room - engineering)
        :precondition
            (and
                (damaged ?s)
                (at ?p ?room)
            )
        :effect
            (not (damaged ?s))
    )
    
    ; all personal can pick-up light equipment
    (:action pick-up
        :parameters
            (?x - personnel ?y - light ?room - static)
        :precondition
            (and
				(pickable ?y)
                (at ?x ?room)
                (equipment-location ?y ?room)
                
                (not (is-carrying ?x))
                (not (carrying ?x ?y))
            )
        :effect
        (and
            (carrying ?x ?y)
            (is-carrying ?x)
            (not (equipment-location ?y ?room))
        )
    )
    
    ; only robots can pick-up heavy equipment
    (:action pick-up-robot
        :parameters
            (?x - robot ?y - (either heavy plasma) ?room - static)
        :precondition
            (and
				(pickable ?y)
				(not (is-carrying ?x))
                (not (carrying ?x ?y))
                (at ?x ?room)
                (equipment-location ?y ?room)
                (charged ?x)
            )
        :effect
        (and
            (carrying ?x ?y)
            (is-carrying ?x)
            (not (equipment-location ?y ?room))
        )
    )
)
