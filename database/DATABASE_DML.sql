
-- Route 1 
INSERT INTO ROUTE (AFSTAND) VALUES (10);

INSERT INTO COORDINATES (LATITUDE, LONGITUDE, ALTITUDE) VALUES (52.030944, 5.674306, 28),
                                                              (52.030033, 5.675282, 27),
                                                              (52.030257, 5.679166, 26),
                                                               (52.034237, 5.679981, 27);

INSERT INTO SEGMENT ( STARTCOORD, ENDCOORD) VALUES (1, 2),
                                                   (2, 3),
                                                   (3, 4);

INSERT INTO SEGMENTINROUTE VALUES (1, 1, null),
                                   (1, 2, 1),
                                   (1, 3, 2);

INSERT INTO POI VALUES (1, 'Connectkerk', 'Kerk');

-- Route 2 
INSERT INTO ROUTE (AFSTAND) VALUES (7);

INSERT INTO COORDINATES (LATITUDE, LONGITUDE, ALTITUDE) VALUES (52.033273, 5.676709, 23),
                               (52.030025, 5.675867, 21),
                               (52.030034, 5.675301, 24);
                               

INSERT INTO SEGMENT ( STARTCOORD, ENDCOORD) VALUES (5, 6),
                                                   (6, 7),
                                                   (7, 1);

INSERT INTO SEGMENTINROUTE VALUES  (2, 4, null),
                                   (2, 5, 4),
                                   (2, 6, 5);

INSERT INTO POI (SEGMENTID, name) VALUES (5, 'Rival Foods');
                                

                           

