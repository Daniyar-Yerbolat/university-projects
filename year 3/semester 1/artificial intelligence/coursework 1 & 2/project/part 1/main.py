from a_star import *
from random import randint

def cls():
    print ("\n" * 50)

class objs:
    def __init__(self, large, small, mediumA, mediumB, pos):
        self.large = large
        self.small = small
        self.mediumA = mediumA
        self.mediumB = mediumB
        self.pos = pos
class robo:
    def __init__(self, pos):
        self.pos = pos
        self.carry = 0
print("Which heuristics do you want the program to use?")
heuristic =input(" Enter M for Manhattan or E for Euclidean")        

truck = objs(randint(1, 9),randint(1, 9), 0, 0, (4, 6))
warehouseA = objs(0, 0, randint(1, 9), 0, (6, 8))
warehouseB = objs(0, 0, 0, randint(1, 9), (6, 3))
robot=robo((0,0))
print("The truck contains \n" + str(truck.small) + " small packages \n" +str(truck.large) + " large packages \n" +str(truck.mediumA) + " medium A packages \n" + str(truck.mediumB)+ " medium B packages \n")
print("The Warehouse A contains \n" + str(warehouseA.small) + " small packages \n" + str(warehouseA.large) + " large packages \n" + str(warehouseA.mediumA), "medium A packages \n" + str(warehouseA.mediumB)+ " medium B packages \n")
print("The Warehouse B contains \n" + str(warehouseB.small)+ " small packages \n"+ str(warehouseB.large)+ " large packages \n" +str(warehouseB.mediumA)+ " medium A packages \n" + str(warehouseB.mediumB)+ " medium B packages \n")
draw_grid(diagram4, width=3, truck=truck.pos, warehouseA=warehouseA.pos, warehouseB=warehouseB.pos)
print()

current = truck

print("Robot is at the delivery truck")
print("Robot is not carrying anything")
input("Press Enter :")
cls()
while truck.small!=0 or warehouseA.mediumA!=0:
    if current == truck:
        if truck.small != 0:
            truck.small = truck.small - 1
            robot.carry = 1
            print("Robot is carrying small package from truck to warehouse A")
            came_from, cost_so_far,startheuristics = a_star_search(diagram4, current.pos, warehouseA.pos,heuristic)
            path1=reconstruct_path(came_from,start=current.pos, goal=warehouseA.pos)
            draw_grid(diagram4, width=3, path=path1[1:-1],truck=truck.pos, warehouseA=warehouseA.pos,warehouseB=warehouseB.pos)
            print("Cost of the path : " + str(cost_so_far[warehouseA.pos]))
            print("Heuristis of the start from the goal  " +startheuristics)
            warehouseA.small = warehouseA.small + 1
            print("The truck contains \n" + str(truck.small) + " small packages \n" +str(truck.large) + " large packages \n" +str(truck.mediumA) + " medium A packages \n" + str(truck.mediumB)+ " medium B packages \n")
            print("The Warehouse A contains \n" + str(warehouseA.small) + " small packages \n" + str(warehouseA.large) + " large packages \n" + str(warehouseA.mediumA), "medium A packages \n" + str(warehouseA.mediumB)+ " medium B packages \n")
            print("The Warehouse B contains \n" + str(warehouseB.small)+ " small packages \n"+ str(warehouseB.large)+ " large packages \n" +str(warehouseB.mediumA)+ " medium A packages \n" + str(warehouseB.mediumB)+ " medium B packages \n")
            robot.carry = 0
            current = warehouseA
            input("Press Enter :")
            cls()
        else:
            print("Robot is not carrying anything from truck to warehouse A")
            came_from, cost_so_far,startheuristics = a_star_search(diagram4, current.pos, warehouseA.pos,heuristic)
            path1=reconstruct_path(came_from,start=current.pos, goal=warehouseA.pos)
            draw_grid(diagram4, width=3, path=path1[1:-1],truck=truck.pos, warehouseA=warehouseA.pos,warehouseB=warehouseB.pos)
            print("Cost of the path : " + str(cost_so_far[warehouseA.pos]))
            print("Heuristis of the start from the goal  " +startheuristics)
            print("The truck contains \n" + str(truck.small) + " small packages \n" +str(truck.large) + " large packages \n" +str(truck.mediumA) + " medium A packages \n" + str(truck.mediumB)+ " medium B packages \n")
            print("The Warehouse A contains \n" + str(warehouseA.small) + " small packages \n" + str(warehouseA.large) + " large packages \n" + str(warehouseA.mediumA), "medium A packages \n" + str(warehouseA.mediumB)+ " medium B packages \n")
            print("The Warehouse B contains \n" + str(warehouseB.small)+ " small packages \n"+ str(warehouseB.large)+ " large packages \n" +str(warehouseB.mediumA)+ " medium A packages \n" + str(warehouseB.mediumB)+ " medium B packages \n")

            current = warehouseA
            input("Press Enter :")
            cls()
            
    elif current == warehouseA:
        if warehouseA.mediumA != 0:
            warehouseA.mediumA = warehouseA.mediumA - 1
            robot.carry = 1
            print("Robot is carrying medium package from warehouse A to truck")
            came_from, cost_so_far,startheuristics = a_star_search(diagram4, current.pos, truck.pos,heuristic)
            path1=reconstruct_path(came_from,start=current.pos, goal=truck.pos)
            draw_grid(diagram4, width=3, path=path1[1:-1],truck=truck.pos, warehouseA=warehouseA.pos,warehouseB=warehouseB.pos)
            print("Cost of the path : " + str(cost_so_far[truck.pos]))
            print("Heuristis of the start from the goal  " +startheuristics)
            truck.mediumA = truck.mediumA + 1
            robot.carry = 0
            
            print("The truck contains \n" + str(truck.small) + " small packages \n" +str(truck.large) + " large packages \n" +str(truck.mediumA) + " medium A packages \n" + str(truck.mediumB)+ " medium B packages \n")
            print("The Warehouse A contains \n" + str(warehouseA.small) + " small packages \n" + str(warehouseA.large) + " large packages \n" + str(warehouseA.mediumA), "medium A packages \n" + str(warehouseA.mediumB)+ " medium B packages \n")
            print("The Warehouse B contains \n" + str(warehouseB.small)+ " small packages \n"+ str(warehouseB.large)+ " large packages \n" +str(warehouseB.mediumA)+ " medium A packages \n" + str(warehouseB.mediumB)+ " medium B packages \n")
            current = truck
            input("Press Enter :")
            cls()
            
        else:
            print("Robot is not carrying anything from warehouse A to truck")
            
            came_from, cost_so_far,startheuristics = a_star_search(diagram4, current.pos, truck.pos,heuristic)
            path1=reconstruct_path(came_from,start=current.pos, goal=truck.pos)
            draw_grid(diagram4, width=3, path=path1[1:-1],truck=truck.pos, warehouseA=warehouseA.pos,warehouseB=warehouseB.pos)
            print("Cost of the path : " + str(cost_so_far[truck.pos]))
            print("Heuristis of the start from the goal  " +startheuristics)
            print("The truck contains \n" + str(truck.small) + " small packages \n" +str(truck.large) + " large packages \n" +str(truck.mediumA) + " medium A packages \n" + str(truck.mediumB)+ " medium B packages \n")
            print("The Warehouse A contains \n" + str(warehouseA.small) + " small packages \n" + str(warehouseA.large) + " large packages \n" + str(warehouseA.mediumA), "medium A packages \n" + str(warehouseA.mediumB)+ " medium B packages \n")
            print("The Warehouse B contains \n" + str(warehouseB.small)+ " small packages \n"+ str(warehouseB.large)+ " large packages \n" +str(warehouseB.mediumA)+ " medium A packages \n" + str(warehouseB.mediumB)+ " medium B packages \n")
            input("Press Enter :")
            current = truck
            cls()

current = truck
while truck.large!= 0 or warehouseB.mediumB!= 0:
    if current == warehouseB:
        if warehouseB.mediumB != 0:
            warehouseB.mediumB = warehouseB.mediumB - 1
            robot.carry = 1
            print("Robot is carrying medium package from warehouse B to truck")
            came_from, cost_so_far, startheuristics = a_star_search(diagram4, current.pos, truck.pos,heuristic)
            path1=reconstruct_path(came_from,start=current.pos, goal=truck.pos)
            draw_grid(diagram4, width=3, path=path1[1:-1],truck=truck.pos, warehouseA=warehouseA.pos,warehouseB=warehouseB.pos)
            print("Cost of the path : " + str(cost_so_far[truck.pos]))
            print("Heuristis of the start from the goal  " +startheuristics)
            truck.mediumB = truck.mediumB + 1
            robot.carry = 0
                
                
            print("The truck contains \n" + str(truck.small) + " small packages \n" +str(truck.large) + " large packages \n" +str(truck.mediumA) + " medium A packages \n" + str(truck.mediumB)+ " medium B packages \n")
            print("The Warehouse A contains \n" + str(warehouseA.small) + " small packages \n" + str(warehouseA.large) + " large packages \n" + str(warehouseA.mediumA), "medium A packages \n" + str(warehouseA.mediumB)+ " medium B packages \n")
            print("The Warehouse B contains \n" + str(warehouseB.small)+ " small packages \n"+ str(warehouseB.large)+ " large packages \n" +str(warehouseB.mediumA)+ " medium A packages \n" + str(warehouseB.mediumB)+ " medium B packages \n")    
                
            current = truck
            input("Press Enter :")
            cls()
        else:
            print("Robot is not carrying anything from warehouse B to truck")
          
            came_from, cost_so_far, startheuristics = a_star_search(diagram4, current.pos, truck.pos,heuristic)
            path1=reconstruct_path(came_from,start=current.pos, goal=truck.pos)
            draw_grid(diagram4, width=3, path=path1[1:-1],truck=truck.pos, warehouseA=warehouseA.pos,warehouseB=warehouseB.pos)
            print("Cost of the path : " + str(cost_so_far[truck.pos]))
            print("Heuristis of the start from the goal  " +startheuristics)
            print("The truck contains \n" + str(truck.small) + " small packages \n" +str(truck.large) + " large packages \n" +str(truck.mediumA) + " medium A packages \n" + str(truck.mediumB)+ " medium B packages \n")
            print("The Warehouse A contains \n" + str(warehouseA.small) + " small packages \n" + str(warehouseA.large) + " large packages \n" + str(warehouseA.mediumA), "medium A packages \n" + str(warehouseA.mediumB)+ " medium B packages \n")
            print("The Warehouse B contains \n" + str(warehouseB.small)+ " small packages \n"+ str(warehouseB.large)+ " large packages \n" +str(warehouseB.mediumA)+ " medium A packages \n" + str(warehouseB.mediumB)+ " medium B packages \n")
            input("Press Enter :")
            current = truck
            cls()
            
    elif current == truck:
        if truck.large != 0:
            truck.large = truck.large - 1
            robot.carry = 1
            print("Robot is carrying large package from truck to warehouse B")
            came_from, cost_so_far,startheuristics = a_star_search(diagram4, current.pos, warehouseB.pos,heuristic)
            path1=reconstruct_path(came_from,start=current.pos, goal=warehouseB.pos)
            draw_grid(diagram4, width=3, path=path1[1:-1],truck=truck.pos, warehouseA=warehouseA.pos,warehouseB=warehouseB.pos)
            print("Cost of the path : " + str(cost_so_far[warehouseB.pos]))
            print("Heuristis of the start from the goal  " +startheuristics)
            warehouseB.large = warehouseB.large + 1
            robot.carry = 0
            current = warehouseB
            
            print("The truck contains \n" + str(truck.small) + " small packages \n" +str(truck.large) + " large packages \n" +str(truck.mediumA) + " medium A packages \n" + str(truck.mediumB)+ " medium B packages \n")
            print("The Warehouse A contains \n" + str(warehouseA.small) + " small packages \n" + str(warehouseA.large) + " large packages \n" + str(warehouseA.mediumA), "medium A packages \n" + str(warehouseA.mediumB)+ " medium B packages \n")
            print("The Warehouse B contains \n" + str(warehouseB.small)+ " small packages \n"+ str(warehouseB.large)+ " large packages \n" +str(warehouseB.mediumA)+ " medium A packages \n" + str(warehouseB.mediumB)+ " medium B packages \n")

            input("Press Enter :")
            cls()
        else:
            print("Robot is not carrying anything from truck to warehouse B")
            came_from, cost_so_far,startheuristics = a_star_search(diagram4, current.pos, warehouseB.pos,heuristic)
            path1=reconstruct_path(came_from,start=current.pos, goal=warehouseB.pos)
            draw_grid(diagram4, width=3, path=path1[1:-1],truck=truck.pos, warehouseA=warehouseA.pos,warehouseB=warehouseB.pos)
            print("Cost of the path : " + str(cost_so_far[warehouseB.pos]))
            print("Heuristis of the start from the goal  " +startheuristics)
            print("The truck contains \n" + str(truck.small) + " small packages \n" +str(truck.large) + " large packages \n" +str(truck.mediumA) + " medium A packages \n" + str(truck.mediumB)+ " medium B packages \n")
            print("The Warehouse A contains \n" + str(warehouseA.small) + " small packages \n" + str(warehouseA.large) + " large packages \n" + str(warehouseA.mediumA), "medium A packages \n" + str(warehouseA.mediumB)+ " medium B packages \n")
            print("The Warehouse B contains \n" + str(warehouseB.small)+ " small packages \n"+ str(warehouseB.large)+ " large packages \n" +str(warehouseB.mediumA)+ " medium A packages \n" + str(warehouseB.mediumB)+ " medium B packages \n")
            current = warehouseB
            input("Press Enter :")
            cls()
        
print("The Packages are in their respective areas. Its a Successs!!!")             

print("The truck contains \n" + str(truck.small) + " small packages \n" +str(truck.large) + " large packages \n" +str(truck.mediumA) + " medium A packages \n" + str(truck.mediumB)+ " medium B packages \n")
print("The Warehouse A contains \n" + str(warehouseA.small) + " small packages \n" + str(warehouseA.large) + " large packages \n" + str(warehouseA.mediumA), " medium A packages \n" + str(warehouseA.mediumB)+ " medium B packages \n")
print("The Warehouse B contains \n" + str(warehouseB.small)+ " small packages \n"+ str(warehouseB.large)+ " large packages \n" +str(warehouseB.mediumA)+ " medium A packages \n" + str(warehouseB.mediumB)+ " medium B packages \n")
        
