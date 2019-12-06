FROM websphere-liberty:19.0.0.9-kernel-java11

COPY --chown=1001:0  TestServletWebProject.war /config/apps/TestServletWebProject.war
COPY --chown=1001:0  server.xml /config/

COPY --chown=1001:0  db2jcc4.jar /config/
COPY --chown=1001:0  db2jcc_license_cu.jar /config/


ARG MP_MONITORING=true

RUN configure.sh